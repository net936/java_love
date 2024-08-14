// 权限问题后期增加
import { get, post } from '/@/utils/http/axios';
// import axios from 'axios';
const URL = {
  list: '/api/thing/list',
  create: '/api/thing/create',
  update: '/api/thing/update',
  delete: '/api/thing/delete',
  detail: '/api/thing/detail',
  listUserThing: '/api/thing/listUserThing',
  recommend: '/api/thing/recommend',
  rate: '/api/thing/rate',
};

const listApi = async (params) => get({ url: URL.list, params: params, data: {}, headers: {} });
const listUserThingApi = async (params) => get({ url: URL.listUserThing, params: params, data: {}, headers: {} });
const createApi = async (data) =>
  post({ url: URL.create, params: {}, data: data, headers: { 'Content-Type': 'multipart/form-data;charset=utf-8' } });
const updateApi = async (data) => post({ url: URL.update, data: data, headers: { 'Content-Type': 'multipart/form-data;charset=utf-8' } });
const deleteApi = async (params) => post({ url: URL.delete, params: params, headers: {} });
const detailApi = async (params) => get({ url: URL.detail, params: params, headers: {} });

const getRecommendApi = async (params) => get({ url: URL.recommend, params: params, headers: {} });
const rateApi = async (params) => post({ url: URL.rate, params: params, headers: {} });

export { listApi, createApi, updateApi, deleteApi, detailApi, listUserThingApi, getRecommendApi, rateApi };
