import {get, post} from '/@/utils/http/axios';

const URL= {
    list: '/api/order/list',
    create: '/api/order/create',
    update: '/api/order/update',
    delete: '/api/order/delete',
    cancel: '/api/order/cancelOrder',
    yesUserOrder: '/api/order/yesUserOrder',
    cancelUserOrder: '/api/order/cancelUserOrder',
    userOrderList: '/api/order/userOrderList',
}

const listApi = async (params) =>
    get({url: URL.list, params: params, data: {}, headers: {}});
const userOrderListApi = async (params) =>
    get({url: URL.userOrderList, params: params, data: {}, headers: {}});

const createApi = async (data) =>
    post({
        url: URL.create,
        params: {},
        data: data,
        headers: {'Content-Type': 'multipart/form-data;charset=utf-8'}
    });
const updateApi = async (params, data) =>
    post({
        url: URL.update,
        params: params,
        data: data,
        headers: {'Content-Type': 'multipart/form-data;charset=utf-8'}
    });
const deleteApi = async (params) =>
    post({url: URL.delete, params: params, headers: {}});

const cancelApi = async (params) =>
    post({url: URL.cancel, params: params, headers: {}});

const yesUserOrderApi = async (params) =>
    post({url: URL.yesUserOrder, params: params, headers: {}});
const cancelUserOrderApi = async (params) =>
    post({url: URL.cancelUserOrder, params: params, headers: {}});

export {listApi, userOrderListApi, createApi, updateApi, deleteApi, cancelApi,yesUserOrderApi, cancelUserOrderApi};
