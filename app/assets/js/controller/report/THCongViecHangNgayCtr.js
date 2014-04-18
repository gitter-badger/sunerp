/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.report.THCongViecHangNgayCtr', {
    extend: 'sunerp.controller.core.BaseReportCtr',
    control: {
        donViCb: {
            selector: 'donvicb'
        },
        phongBanCb: {
            selector: 'phongbancb'
        },
        monthCb: {
            selector: 'monthcb'
        },
        yearCb: {
            selector: 'yearcb'
        },
        iframe: {
            selector: 'uxiframe'
        }
    },
    doReport: function () {
        var me = this;
        me.request('/report/khoiluongreport', me.getParams(), function (response) {
            me.getIframe().load('/report/file/' + response.responseText)
        });
    },
    doDownloadPdf: function () {
        var me = this;
        me.request('/report/khoiluongreport?fileType=pdf', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    doDownloadXls: function () {
        var me = this;
        me.request('/report/khoiluongreport?fileType=xls', me.getParams(), function (response) {
            me.showMsg('/report/file/' + response.responseText + '?download=true')
        });
    },
    getParams: function () {
        var me = this;
        return {
            month: me.getMonthCb().getValue(),
            year: me.getYearCb().getValue(),
            donViId: me.getDonViCb().getValue(),
            phongBanId: me.getPhongBanCb().getValue()
        }
    }
});