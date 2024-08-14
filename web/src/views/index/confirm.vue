<template>
  <div>
    <Header />
    <section class="cart-page flex-view">
      <div class="left-flex">
        <div class="title flex-view">
          <h3>申请约会</h3>
        </div>
        <div class="cart-list-view">
          <div class="list-th flex-view">
            <span class="line-1">对方姓名</span>
            <span class="line-2">性别</span>
            <span class="line-5">年龄</span>
          </div>
          <div class="list">
            <div class="items flex-view">
              <div class="book flex-view">
                <img :src="pageData.cover" />
                <h2>{{ pageData.title }}</h2>
              </div>
              <div class="pay">{{ pageData.sex }}</div>
              <div class="pay">{{ pageData.age }}</div>
            </div>
          </div>
        </div>

        <div class="title flex-view">
          <h3>您的姓名</h3>
        </div>
        <input v-model="pageData.receiverName" placeholder="请输入姓名" class="name" />

        <div class="title flex-view">
          <h3>您的电话</h3>
        </div>
        <input v-model="pageData.receiverPhone" placeholder="请输入手机号" class="name" />

        <div class="title flex-view">
          <h3>约会地址</h3>
        </div>
        <input v-model="pageData.receiverAddress" placeholder="请输入地址" class="name" />

        <div class="title flex-view">
          <h3>约会时间</h3>
        </div>
        <input v-model="pageData.receiverTime" placeholder="请输入时间" class="name" />

        <div class="title flex-view">
          <h3>备注</h3>
        </div>
        <input v-model="pageData.remark" placeholder="输入备注信息，100字以内" class="name" />
      </div>
      <div class="right-flex">
        <div class="price-view">
          <div class="btns-view">
            <button class="btn buy" @click="handleBack()">返回</button>
            <button class="btn pay jiesuan" @click="handleCommit()">提交</button>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
  import { message } from 'ant-design-vue';
  import Header from '/@/views/index/components/header.vue';
  import Footer from '/@/views/index/components/footer.vue';
  import DeleteIcon from '/@/assets/images/delete-icon.svg';
  import { createApi } from '/@/api/order';
  import { useUserStore } from '/@/store';

  const router = useRouter();
  const route = useRoute();
  const userStore = useUserStore();

  const pageData = reactive({
    id: undefined,
    title: undefined,
    cover: undefined,
    price: undefined,
    remark: undefined,
    sex: undefined,
    age: undefined,
    receiverName: undefined,
    receiverPhone: undefined,
    receiverAddress: undefined,
    receiverTime: undefined
  });

  const myform = ref();

  onMounted(() => {
    pageData.fromId = route.query.fromId;
    pageData.id = route.query.id;
    pageData.title = route.query.title;
    pageData.cover = route.query.cover;
    pageData.age = route.query.age;
    pageData.sex = route.query.sex;
  });

  // 恢复表单初始状态
  const resetModal = () => {
    myform.value?.resetFields();
  };

  const onCountChange = (value) => {
    pageData.amount = pageData.price * value;
  };

  const handleBack = () => {
    router.back();
    console.log('back...');
  };
  const handleCommit = () => {
    const formData = new FormData();
    let userId = userStore.user_id;
    if (!userId) {
      message.warn('请先登录！');
      return;
    }
    if (!pageData.receiverName) {
      message.warn('请输入姓名！');
      return;
    }
    formData.append('thingA', pageData.fromId);
    formData.append('thingB', pageData.id);
    formData.append('thingBTitle', pageData.title);
    formData.append('remark', pageData.remark || '');
    formData.append('receiverName', pageData.receiverName || '');
    formData.append('receiverPhone', pageData.receiverPhone || '');
    formData.append('receiverAddress', pageData.receiverAddress || '');
    formData.append('receiverTime', pageData.receiverTime || '');
    console.log(formData);
    createApi(formData)
      .then((res) => {
        message.success('提交成功');
        router.push({ name: 'success', query: { title: pageData.title}, replace: true });
      })
      .catch((err) => {
        message.error(err.msg || '操作失败');
      });
  };
</script>

<style scoped lang="less">
  .flex-view {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
  }

  .cart-page {
    width: 95%;
    max-width: 800px;
    min-height: 50vh;
    margin: 100px auto;
    flex-direction: column;
  }

  .left-flex {
    -webkit-box-flex: 17;
    -ms-flex: 17;
    flex: 17;
    padding-right: 20px;
  }

  .title {
    margin-top: 20px;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;

    h3 {
      color: #152844;
      font-weight: 600;
      font-size: 16px;
      margin: 0;
    }
  }

  .cart-list-view {
    margin: 4px 0 40px;

    .list-th {
      height: 42px;
      line-height: 42px;
      border-bottom: 1px solid #cedce4;
      color: #152844;
      font-size: 14px;

      .line-1 {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        margin-right: 20px;
      }

      .line-2,
      .pc-style .cart-list-view .list-th .line-3,
      .pc-style .cart-list-view .list-th .line-4 {
        width: 65px;
        margin-right: 30px;
      }

      .line-5 {
        width: 40px;
        margin-right: 40px;
      }

      .line-6 {
        width: 28px;
      }
    }
  }

  .items {
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    margin-top: 20px;

    .book {
      -webkit-box-flex: 1;
      -ms-flex: 1;
      flex: 1;
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
      margin-right: 20px;
      cursor: pointer;

      img {
        width: 48px;
        margin-right: 16px;
        border-radius: 4px;
      }

      h2 {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        font-size: 14px;
        line-height: 22px;
        color: #152844;
      }
    }

    .type {
      width: 65px;
      margin-right: 20px;
      color: #152844;
      font-size: 14px;
    }

    .pay {
      color: #ff8a00;
      font-weight: 600;
      font-size: 16px;
      width: 65px;
      margin-right: 20px;
    }

    .num-box {
      width: 80px;
      margin-right: 43px;
      border-radius: 4px;
      border: 1px solid #cedce4;
      -webkit-box-pack: justify;
      -ms-flex-pack: justify;
      justify-content: space-between;
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
      height: 32px;
      padding: 0 4px;
    }

    .delete {
      margin-left: 36px;
      width: 24px;
      cursor: pointer;
    }
  }

  .mb-24 {
    margin-bottom: 24px;
  }

  .show-txt {
    color: #ff8a00;
    font-size: 14px;
  }

  .name {
    width: 100%;
    background: #f6f9fb;
    border: 0;
    border-radius: 4px;
    padding: 6px 12px;
    //color: #152844;
    margin-top: 16px;
    resize: none;
    height: 30px;
    line-height: 22px;
  }

  .remark {
    width: 100%;
    background: #f6f9fb;
    border: 0;
    border-radius: 4px;
    padding: 6px 12px;
    //color: #152844;
    margin-top: 16px;
    resize: none;
    height: 56px;
    line-height: 22px;
  }

  .right-flex {
    -webkit-box-flex: 8;
    -ms-flex: 8;
    flex: 8;
  }

  .click-txt {
    color: #4684e2;
    font-size: 14px;
    cursor: pointer;
  }

  .address-view {
    margin: 12px 0 24px;

    .info {
      color: #909090;
      font-size: 14px;

      .info-blue {
        cursor: pointer;
        color: #4684e2;
      }
    }

    .name {
      color: #152844;
      font-weight: 500;
    }

    .tel {
      color: #152844;
      float: right;
    }

    .address {
      color: #152844;
      margin-top: 4px;
    }
  }

  .price-view {
    overflow: hidden;
    margin-top: 16px;

    .price-item {
      -webkit-box-pack: justify;
      -ms-flex-pack: justify;
      justify-content: space-between;
      -webkit-box-align: center;
      -ms-flex-align: center;
      align-items: center;
      margin-bottom: 8px;
      font-size: 14px;

      .item-name {
        color: #152844;
      }

      .price-txt {
        font-weight: 500;
        color: #ff8a00;
      }
    }

    .total-price-view {
      margin-top: 12px;
      border-top: 1px solid #cedce4;
      -webkit-box-pack: justify;
      -ms-flex-pack: justify;
      justify-content: space-between;
      -webkit-box-align: start;
      -ms-flex-align: start;
      align-items: flex-start;
      padding-top: 10px;
      color: #152844;
      font-weight: 500;

      .price {
        color: #ff8a00;
        font-size: 16px;
        height: 36px;
        line-height: 36px;
      }
    }

    .btns-view {
      margin-top: 24px;
      text-align: center;

      .buy {
        background: #fff;
        color: #4684e2;
      }

      .jiesuan {
        cursor: pointer;
        background: #4684e2;
        color: #fff;
      }

      .btn {
        cursor: pointer;
        width: 96px;
        height: 36px;
        line-height: 33px;
        margin-left: 16px;
        text-align: center;
        border-radius: 32px;
        border: 1px solid #4684e2;
        font-size: 16px;
        outline: none;
      }
    }
  }
</style>
