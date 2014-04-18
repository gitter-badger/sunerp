package services

import com.escalatesoft.subcut.inject.BindingModule
import play.api.db.slick.Session
import play.api.Play
import play.api.Play.current
import dtos.report._
import models.sunerp._
import models.qlkh.Tasks
import play.api.db.slick.Config.driver.simple._
import dtos.report.DonViDto
import dtos.report.PhongBanDto
import dtos.report.KhoiLuongDto
import models.sunerp.DonVi
import models.sunerp.PhongBan
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder
import dtos.report.row.DonViKhoiLuongRow

/**
 * The Class KhoiLuongReportService.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 2:10 PM
 *
 */
trait KhoiLuongReportService {

  /**
   * Build report for a "PhongBan".
   * @param session
   * @return report file name
   */
  def doPhongBanReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String

  def doDonViReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String

}

import net.sf.dynamicreports.report.builder.DynamicReports._

class KhoiLuongReportServiceImpl(implicit val bindingModule: BindingModule) extends KhoiLuongReportService {

  val reportDir = "report/"

  override def doDonViReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String = {
    val fileName = s"khoiluong-${req.donViNameStrip}-thang${req.month}-nam${req.year}"
    //build layout
    val report = KhoiLuongReportColumnBuilder.buildDonViLayout(req)
    val donViDto = buildDonViData(req.month, req.year, req.getDonVi)

    val row = new DonViKhoiLuongRow

    import scala.collection.JavaConverters._

    //    //build data
    val ds = new JRBeanCollectionDataSource(List(row, row).asJava)
    report.setDataSource(ds)
    exportReport(fileType, fileName, report)
  }

  override def doPhongBanReport(fileType: String, req: KhoiLuongReportRequest)(implicit session: Session): String = {
    val fileName = s"khoiluong-${req.donViNameStrip}-${req.phongBanNameStrip}-thang${req.month}-nam${req.year}"
    //build layout
    val report = KhoiLuongReportColumnBuilder.buildPhongBanLayout(req)
    val phongBanDto = buildPhongBanData(req.month, req.year, req.getPhongBan)
    //build data
    val ds = new JRBeanCollectionDataSource(phongBanDto.javaReportRows())
    report.setDataSource(ds)

    exportReport(fileType, fileName, report)
  }

  private def exportReport(fileType: String, fileName: String, report: JasperReportBuilder) = {
    val pdfFile = fileName + ".pdf"
    val xlsFile = fileName + ".xls"
    val pdfExporter = export.pdfExporter(Play.application.getFile(reportDir + pdfFile))
    val xlsExporter = export.xlsExporter(Play.application.getFile(reportDir + xlsFile))
    fileType match {
      case "pdf" =>
        report.toPdf(pdfExporter)
        pdfFile
      case "xls" =>
        report.toXls(xlsExporter)
        xlsFile
      case _ => ???
    }
  }

  private def buildCongTyData(month: Int, year: Int)(implicit session: Session) = {
    val donVis = for (donVi <- DonVis.all) yield buildDonViData(month, year, donVi)
    CongTyDto(donVis = donVis)
  }

  private def buildDonViData(month: Int, year: Int, donVi: DonVi)(implicit session: Session) = {
    val phongBans = for {
      phongBan <- PhongBans.findByDonViId(donVi.getId)
    } yield buildPhongBanData(month, year, phongBan)
    DonViDto(
      id = donVi.getId,
      name = donVi.name,
      phongBans = phongBans
    )
  }

  private def buildPhongBanData(month: Int, year: Int, phongBan: PhongBan)(implicit session: Session) = {
    val tasks = Tasks.all

    val query = for {
      soPhanCong <- SoPhanCongs.soPhanCongKhoiLuongQuery(month, year, phongBan.getId)
      nhanVien <- soPhanCong.nhanVien
    } yield (soPhanCong, nhanVien)

    val result = query.list()

    val khoiLuongs = for (tuple <- result) yield {
      val (soPhanCong, nhanVien) = tuple
      //make sure every task in so phan cong always exits.
      val task = tasks.find(_.id == soPhanCong.taskId).get
      KhoiLuongDto(
        task = TaskDto(task),
        nhanVien = dtos.report.NhanVienDto(nhanVien),
        khoiLuong = soPhanCong.khoiLuong,
        gio = soPhanCong.gio,
        ngayPhanCong = soPhanCong.ngayPhanCong
      )
    }

    PhongBanDto(
      id = phongBan.getId,
      name = phongBan.name,
      khoiLuongs = khoiLuongs
    )
  }

}
