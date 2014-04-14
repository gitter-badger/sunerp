package config

import com.escalatesoft.subcut.inject.Injectable
import controllers._

/**
 * The Class ApplicationContext.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 12:02 PM
 *
 */
object ApplicationContext extends Injectable {
  implicit val bindingModule = ApplicationConfiguration
  val homeCtr = inject[HomeCtr]
  val homeCtrClass = classOf[HomeCtr]
  val reportCtr = inject[ReportCtr]
  val reportCtrClass = classOf[ReportCtr]
  val chuVuCtr = inject[ChucVuCtr]
  val chuVuCtrClass = classOf[ChucVuCtr]
  val adminCtr = inject[AdminCtr]
  val adminCtrClass = classOf[AdminCtr]
  val authCtr = inject[AuthCtr]
  val authCtrClass = classOf[AuthCtr]
  val companyCtr = inject[CompanyCtr]
  val companyCtrClass = classOf[CompanyCtr]
  val donViCtr = inject[DonViCtr]
  val donViCtrClass = classOf[DonViCtr]
  val nhanVienCtr = inject[NhanVienCtr]
  val nhanVienCtrClass = classOf[NhanVienCtr]
  val phongBanCtr = inject[PhongBanCtr]
  val phongBanCtrClass = classOf[PhongBanCtr]
  val quyenHanhCtr = inject[QuyenHanhCtr]
  val quyenHanhCtrClass = classOf[QuyenHanhCtr]
  val soLuongCtr = inject[SoLuongCtr]
  val soLuongCtrClass = classOf[SoLuongCtr]
  val soPhanCongCtr = inject[SoPhanCongCtr]
  val soPhanCongCtrClass = classOf[SoPhanCongCtr]
  val taskCtr = inject[TaskCtr]
  val taskCtrClass = classOf[TaskCtr]

  def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerClass match {
      case `homeCtrClass` => homeCtr.asInstanceOf[A]
      case `reportCtrClass` => reportCtr.asInstanceOf[A]
      case `chuVuCtrClass` => chuVuCtr.asInstanceOf[A]
      case `adminCtrClass` => adminCtr.asInstanceOf[A]
      case `authCtrClass` => authCtr.asInstanceOf[A]
      case `companyCtrClass` => companyCtr.asInstanceOf[A]
      case `donViCtrClass` => donViCtr.asInstanceOf[A]
      case `phongBanCtrClass` => phongBanCtr.asInstanceOf[A]
      case `quyenHanhCtrClass` => quyenHanhCtr.asInstanceOf[A]
      case `soLuongCtrClass` => soLuongCtr.asInstanceOf[A]
      case `soPhanCongCtrClass` => soPhanCongCtr.asInstanceOf[A]
      case `taskCtrClass` => taskCtr.asInstanceOf[A]
      case _ => throw new IllegalArgumentException
    }
  }

}