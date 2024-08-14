<template>
  <div class="content-list">
    <div class="list-title">约会列表</div>
    <a-tabs v-model:activeKey="activeKey" style="margin-top: 20px;">
      <a-tab-pane key="1" tab="我约别人">
        <div class="list-content">
          <div class="order-item-view" v-for="(item, index) in orderData" :key="index">
            <div class="header flex-view">
              <div class="left">
                <span class="text">编号</span>
                <span class="num mg-4">#</span>
                <span class="num">{{ item.orderNumber }}</span>
                <span class="time">{{ getFormatTime(item.orderTime, true) }}</span>
              </div>
              <div class="right">
                <span class="text">状态</span>
                <span class="state">{{ item.status === '1' ? '等待中' : (item.status === '2'?'已同意':'已拒绝') }}</span>
              </div>
            </div>
            <div class="content flex-view">
              <div class="left-list">
                <div class="list-item flex-view">
                  <div class="detail flex-between flex-view">
                    <div class="flex-between flex-top flex-view">
                      <h2 class="name">对方姓名：{{ item.thingbTitle }}</h2>
                    </div>
                    <div class="flex-between flex-center flex-view">
                      约会时间：{{item.receiverTime}}，
                      约会地点：{{item.receiverAddress}}
                    </div>
                  </div>
                </div>
              </div>

            </div>
            <div class="bottom flex-view">
              <div class="left">
                <span class="text"></span>
                <span class="open" @click="handleDetail(item.thingB)">对方简介</span>
              </div>
              <div class="right flex-view">
              </div>
            </div>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane key="2" tab="别人约我" force-render>
        <div class="list-content">
          <div class="order-item-view" v-for="(item, index) in orderData1" :key="index">
            <div class="header flex-view">
              <div class="left">
                <span class="text">编号</span>
                <span class="num mg-4">#</span>
                <span class="num">{{ item.orderNumber }}</span>
                <span class="time">{{ getFormatTime(item.orderTime, true) }}</span>
              </div>
              <div class="right">
                <a-popconfirm v-if="item.status === '1'" title="确定同意？" ok-text="是" cancel-text="否" @confirm="handleYes(item)">
                  <a-button type="primary" size="small" style="margin-right: 24px">同意</a-button>
                </a-popconfirm>
                <a-popconfirm v-if="item.status === '1'" title="确定拒绝？" ok-text="是" cancel-text="否" @confirm="handleNo(item)">
                  <a-button type="primary" size="small" style="margin-right: 24px">拒绝</a-button>
                </a-popconfirm>
                <span class="text">状态</span>
                <span class="state">{{ item.status === '1' ? '等待中' : (item.status === '2'?'已同意':'已拒绝') }}</span>
              </div>
            </div>
            <div class="content flex-view">
              <div class="left-list">
                <div class="list-item flex-view">
                  <div class="detail flex-between flex-view">
                    <div class="flex-between flex-top flex-view">
                      <h2 class="name">对方姓名：{{ item.receiverName }}</h2>
                    </div>
                    <div class="flex-between flex-center flex-view">
                      约会时间：{{item.receiverTime}}，
                      约会地点：{{item.receiverAddress}}
                    </div>
                  </div>
                </div>
              </div>

            </div>
            <div class="bottom flex-view">
              <div class="left">
                <span class="text"></span>
                <span class="open" @click="handleDetail(item.thingA)">对方简介</span>
              </div>
              <div class="right flex-view">
              </div>
            </div>
          </div>
        </div>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup>
import { message } from 'ant-design-vue';
import { userOrderListApi } from '/@/api/order';
import { yesUserOrderApi, cancelUserOrderApi } from '/@/api/order';
import { BASE_URL } from '/@/store/constants';
import { useUserStore } from '/@/store';
import {listUserThingApi} from "/@/api/thing";
import {getFormatTime} from "/@/utils";

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();

const loading = ref(false);
const orderData = ref([]); // 我约
const orderData1 = ref([]); // 被约

let activeKey = ref('1');

let myThingId = undefined;

onMounted(() => {

  getUserThing();
});


const getUserThing = () => {
  loading.value = true;
  let userId = userStore.user_id;
  listUserThingApi({ userId: userId })
      .then((res) => {
        console.log(res);
        if (res.data && res.data.length > 0) {
          let myThing = res.data[0];
          myThingId = myThing.id;
          getOrderList();
          getOrderList1();
        }
      })
      .catch((err) => {
        console.log(err);
      });
};
const getOrderList = () => {
  loading.value = true;
  userOrderListApi({ myThingId: myThingId, type: '1' })
      .then((res) => {
        orderData.value = res.data;
        loading.value = false;
      })
      .catch((err) => {
        console.log(err);
        loading.value = false;
      });
};

const getOrderList1 = () => {
  loading.value = true;
  userOrderListApi({ myThingId: myThingId, type: '2' })
      .then((res) => {
        orderData1.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
};
const handleDetail = (thingId) => {
  // 跳转新页面
  let text = router.resolve({ name: 'detail', query: { id: thingId } });
  window.open(text.href, '_blank');
};

const handleYes = (item) => {
  yesUserOrderApi({
    id: item.id,
  })
      .then((res) => {
        message.success('操作成功');
        getOrderList1();
      })
      .catch((err) => {
        message.error(err.msg || '取消失败');
      });
};

const handleNo = (item) => {
  cancelUserOrderApi({
    id: item.id,
  })
      .then((res) => {
        message.success('操作成功');
        getOrderList1();
      })
      .catch((err) => {
        message.error(err.msg || '取消失败');
      });
};
</script>
<style scoped lang="less">
.flex-view {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
}

.content-list {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;

  .list-title {
    color: #152844;
    font-weight: 600;
    font-size: 18px;
    line-height: 24px;
    height: 24px;
    margin-bottom: 4px;
  }
}

.order-item-view {
  background: #f7f9fb;
  border-radius: 4px;
  padding: 16px;
  margin-top: 12px;

  .header {
    border-bottom: 1px solid #cedce4;
    padding-bottom: 12px;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    font-size: 14px;

    .text {
      color: #6f6f6f;
    }

    .mg-4 {
      margin-left: 4px;
    }

    .num {
      font-weight: 500;
      color: #152844;
    }

    .num {
      font-weight: 500;
      color: #152844;
    }

    .time {
      margin-left: 16px;
      color: #a1adc5;
    }

    .state {
      color: #ff7b31;
      font-weight: 600;
      margin-left: 10px;
    }
  }

  .content {
    padding: 12px 0;
    overflow: hidden;

    .left-list {
      overflow: hidden;
      height: 90px;
      -webkit-box-flex: 2;
      -ms-flex: 2;
      flex: 2;
      padding-right: 16px;

      .list-item {
        height: 60px;
        margin-bottom: 12px;
        overflow: hidden;
        cursor: pointer;
      }

      .thing-img {
        width: 60px;
        height: 60px;
        margin-right: 12px;
        object-fit: cover;
      }

      .detail {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
      }

      .flex-between {
        -webkit-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
      }

      .flex-between {
        -webkit-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
      }

      .flex-top {
        -webkit-box-align: start;
        -ms-flex-align: start;
        align-items: flex-start;
      }

      .name {
        color: #152844;
        font-weight: 600;
        font-size: 14px;
        line-height: 18px;
      }

      .count {
        color: #484848;
        font-size: 12px;
      }

      .flex-between {
        -webkit-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
      }

      .flex-center {
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
      }

      .type {
        color: #6f6f6f;
        font-size: 12px;
      }

      .price {
        color: #ff7b31;
        font-weight: 600;
        font-size: 14px;
      }
    }

    .right-info {
      -webkit-box-flex: 1;
      -ms-flex: 1;
      flex: 1;
      border-left: 1px solid #cedce4;
      padding-left: 12px;
      line-height: 22px;
      font-size: 14px;

      .title {
        color: #6f6f6f;
      }

      .name {
        color: #152844;
      }

      .text {
        color: #484848;
      }

      .mg {
        margin-bottom: 4px;
      }
    }
  }

  .bottom {
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    border-top: 1px solid #cedce4;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    font-size: 14px;
    padding-top: 14px;

    .text {
      color: #6f6f6f;
    }

    .open {
      color: #4684e2;
      margin-left: 8px;
      cursor: pointer;
    }

    .right {
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
    }

    .text {
      color: #6f6f6f;
    }

    .num {
      color: #152844;
      margin: 0 40px 0 8px;
    }

    .money {
      font-weight: 600;
      font-size: 18px;
      color: #ff7b31;
      margin-left: 8px;
    }
  }
}

.order-item-view:first-child {
  margin-top: 16px;
}
</style>
