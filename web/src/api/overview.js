import {get} from '/@/utils/http/axios';

const URL= {
    list: '/api/overview/count',
    sysInfo: '/api/overview/sysInfo',
}

const listApi = async (params) =>
    get({url: URL.list, params: params, data: {}, headers: {}});


const sysInfoApi = async (params) =>
    get({url: URL.sysInfo, params: params, data: {}, headers: {}});

export {listApi, sysInfoApi};
