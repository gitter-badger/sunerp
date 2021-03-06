/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.donvi.DonViList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.donViList',
    controller: 'sunerp.controller.donvi.DonViListCtr',
    inject: ['donViStore'],
    config: {
        donViStore: null
    },
    searchField: 'name',
    initComponent: function () {
        var me = this;
        me.store = this.getDonViStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Tên', dataIndex: 'name', flex: 1},
            {header: 'Tên viết tắt', dataIndex: 'shortName', flex: 1},
            {
                header: 'Công ty',
                dataIndex: 'company.name',
                flex: 1
            },
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];
        me.callParent(arguments);
    }
});