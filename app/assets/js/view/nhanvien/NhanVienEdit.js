/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.nhanvien.NhanVienEdit', {
    extend: 'sunerp.view.core.BaseEditView',
    alias: 'widget.nhanVienEdit',
    title: 'Edit NhanVien',
    controller: 'sunerp.controller.nhanvien.NhanVienEditCtr',
    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'maNv',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Mã NV'
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'password',
                        minLength: 4,
                        allowBlank: false,
                        fieldLabel: 'Mật khẩu'
                    },
                    {
                        xtype: 'textfield',
                        name: 'firstName',
                        allowBlank: false,
                        fieldLabel: 'Tên'
                    },
                    {
                        xtype: 'textfield',
                        name: 'lastName',
                        allowBlank: false,
                        fieldLabel: 'Họ'
                    },
                    {
                        xtype: 'comboboxx',
                        fieldLabel: 'Chức vụ',
                        name: 'chucVuId',
                        modelName: 'chucVu',
                        store: Ext.create('sunerp.store.ChucVuStore'),
                        valueField: 'id',
                        displayField: 'name',
                        allowBlank: false,
                        emptyText: 'Chọn một chức vụ...'
                    },
                    {
                        xtype: 'phongbancb',
                        fieldLabel: 'Đơn vị',
                        name: 'phongBanId',
                        modelName: 'phongBan',
                        allowBlank: false,
                        addShowAll: false,
                        emptyText: 'Chọn một đơn vị...'
                    }
                    //{
                    //    xtype: 'numberfield',
                    //    name: 'heSoLuong',
                    //    minValue:0,
                    //    allowBlank: false,
                    //    fieldLabel: 'Hệ số lương'
                    //}
                ]
            }
        ];

        this.callParent(arguments);
    }
});