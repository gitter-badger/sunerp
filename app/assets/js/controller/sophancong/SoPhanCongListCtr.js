/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListEditController',
    modelClass: 'sunerp.model.SoPhanCong',
    inject: ['soPhanCongStore', 'userService'],
    config: {
        soPhanCongStore: null,
        phongBanId: null,
        userService: null
    },
    init: function () {
        this.mainStore = this.getSoPhanCongStore();
        this.setPhongBanId(this.getUserService().getCurrentUser().phongBanId);
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = Ext.create(this.modelClass);
        var lastModel = this.getView().getStore().last();
        rec.set('phongBanId', this.getPhongBanId());
        if (lastModel != null) {
            rec.set('ngayPhanCong', lastModel.get('ngayPhanCong'));
        } else {
            rec.set('ngayPhanCong', new Date());
        }
        this.mainStore.insert(this.mainStore.count(), rec);
    }
});