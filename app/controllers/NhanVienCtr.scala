package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{PhongBans, NhanViens, NhanVien}
import models.core.AbstractQuery
import play.api.libs.json.{Json, JsValue, Writes}
import dtos.PagingDto
import play.api.db.slick.Session
import jp.t2v.lab.play2.stackc.RequestWithAttributes
import play.api.mvc.AnyContent
import com.escalatesoft.subcut.inject.BindingModule

/**
 * The Class NhanVienCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
class NhanVienCtr(implicit val bindingModule: BindingModule) extends BaseCtr[NhanVien, NhanViens] with MainTemplate {
  override def editForm(implicit session: Session) = NhanViens.editForm

  override implicit val jsonWrite: Writes[NhanVien] = NhanViens.nhanVienJsonFormat
  override val dao: AbstractQuery[NhanVien, NhanViens] = NhanViens
  override val domainName: String = DomainKey.nhanVien

  override protected def doIndex(paging: PagingDto, request: RequestWithAttributes[AnyContent])(implicit session: Session): JsValue = {
    val result = NhanViens.load(paging, loggedIn(request))
    Json.toJson(result)
  }
}
