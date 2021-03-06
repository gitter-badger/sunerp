/**
 * Created by dungvn3000 on 4/21/14.
 */

Ext.define('sunerp.view.report.BCThucHienKhoiLuongQuy', {
    extend: 'sunerp.view.core.BaseReportView',
    controller: 'sunerp.controller.report.BCThucHienKhoiLuongQuyCtr',
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        me.tbar.insert(0, [
            {xtype: 'donvicb', addShowAll: false},
            {xtype: 'phongbancb', addShowAll: false, emptyText: 'Phòng ban'},
            {xtype: 'quartercb', width: 100},
            {xtype: 'yearcb', width: 100}
        ]);
    }
});