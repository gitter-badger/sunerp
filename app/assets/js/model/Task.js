/**
 * Created by dungvn3000 on 3/12/14.
 */

Ext.define('sunerp.model.Task', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'code',
        'name',
        'defaultValue',
        'taskTypeCode',
        'quota',
        'unit'
    ]
});