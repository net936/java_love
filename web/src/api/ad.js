import {get, post} from '/@/utils/http/axios';

const URL = {
    userList:'/api/ad/list',
    create:'/api/ad/create',
    update:'/api/ad/update',
    delete:'/api/ad/delete',
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
