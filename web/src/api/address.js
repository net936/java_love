import {get, post} from '/@/utils/http/axios';

const URL= {
    userList: '/api/address/list',
    create: '/api/address/create',
    update: '/api/address/update',
    delete: '/api/address/delete',
}

const listApi = async (params) =>
    get({url: URL.userList, params: params, data: {}, headers: {}});
const createApi = async (data) =>
    post({
        url: URL.create,
        params: {},
        data: data,
        headers: {'Content-Type': 'multipart/form-data;charset=utf-8'}
    });
const updateApi = async (data) =>
    post({
        url: URL.update,
        data: data,
        headers: {'Content-Type': 'multipart/form-data;charset=utf-8'}
    });
const deleteApi = async (params) =>
    post({url: URL.delete, params: params, headers: {}});

export {listApi, createApi, updateApi, deleteApi};
