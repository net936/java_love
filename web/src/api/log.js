import {get, post} from '/@/utils/http/axios';

const URL= {
    loginLogList: '/api/opLog/loginLogList',
    clearLoginLogList: '/api/opLog/clearLoginLog',
    opLogList: '/api/opLog/list',
    clearOpLogList: '/api/opLog/clearOpLog',
    errorLogList: '/api/errorLog/list',
    clearErrorLogList: '/api/errorLog/clearErrorLog',
}

const listLoginLogApi = async (params) =>
    get({url: URL.loginLogList, params: params, data: {}, headers: {}});
const clearLoginLogApi = async (params) =>
    post({url: URL.clearLoginLogList, params: params, data: {}, headers: {}});

const listOpLogListApi = async (params) =>
    get({url: URL.opLogList, params: params, data: {}, headers: {}});
const clearOpLogListApi = async (params) =>
    post({url: URL.clearOpLogList, params: params, data: {}, headers: {}});

const listErrorLogListApi = async (params) =>
    get({url: URL.errorLogList, params: params, data: {}, headers: {}});
const clearErrorLogListApi = async (params) =>
    post({url: URL.clearErrorLogList, params: params, data: {}, headers: {}});

export {listLoginLogApi, clearLoginLogApi, listOpLogListApi, clearOpLogListApi, listErrorLogListApi, clearErrorLogListApi};
