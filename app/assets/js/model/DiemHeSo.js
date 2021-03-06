Ext.define('sunerp.model.DiemHeSo', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        'year',
        'heSo',
        'phongban',
        {name: 'nhanVien.fullName', mapping: 'nhanVien.fullName'},
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        {name: 'nhanVien.lastName', mapping: 'nhanVien.lastName'},
        {name: 'phongBan.name', mapping: 'phongBan.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBan', name: 'phongBan'}
    ]
});