<template>
  <div class="detail">
    <Header />

    <div class="detail-content">
      <div class="detail-content-top">
        <img class="top-left-img" :src="detailData.cover" />
        <div class="top-right">
          <div class="title">
            <div class="tag">有效</div>
            <div class="title-value">{{ detailData.title }}</div>
          </div>
          <div class="score">
            <div class="pv">{{ detailData.pv }}次浏览</div>
            <a-rate :value="detailData.rate" style="font-size: 16px" @change="clickRate" />
          </div>
          <div class="meta">性别：{{ detailData.sex }}</div>
          <div class="meta">年龄：{{ detailData.age }}</div>
          <div class="meta">身高：{{ detailData.shengao }}</div>
          <div class="meta">地区：{{ detailData.address }}</div>
          <div class="meta">职业：{{ detailData.zhiye }}</div>
          <div class="meta">车房情况：{{ detailData.chefang }}</div>
          <div class="actions">
            <button class="buy-btn" @click="handleOrder(detailData)">
              <img :src="AddIcon" />
              <span>申请约会</span>
            </button>
            <div class="more">
              <div class="btn-like" @click="addToWish()">
                <img :src="WantIcon" style="padding: 3px" />
                <div class="label">点赞（{{ detailData.wishCount }}）</div>
              </div>
              <div class="btn-like" @click="collect()">
                <img :src="RecommendIcon" />
                <div class="label">收藏（{{ detailData.collectCount }}）</div>
              </div>
              <div class="btn-like" @click="share()">
                <img :src="ShareIcon" />
                <div class="label">分享</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="detail-content-bottom">
        <div class="thing-content-view">
          <div class="main-content">
            <a-tabs v-model:activeKey="selectTabIndex">
              <a-tab-pane key="1" tab="简介">
                <!--简介-->
                <div class="thing-intro" :class="selectTabIndex <= 0 ? '' : 'hide'">
                  <p class="text" style=""> {{ detailData.description }}</p>
                </div>
              </a-tab-pane>
              <a-tab-pane key="2" tab="评论" force-render>
                <!--评论-->
                <div class="thing-comment" :class="selectTabIndex > 0 ? '' : 'hide'">
                  <div class="title">发表新的评论</div>
                  <div class="publish flex-view">
                    <img :src="AvatarIcon" class="mine-img" />
                    <input placeholder="说点什么..." class="content-input" ref="commentRef" />
                    <button class="send-btn" @click="sendComment()">发送</button>
                  </div>
                  <div class="tab-view flex-view">
                    <div class="count-text">共有{{ commentData.length }}条评论</div>
                    <div class="tab-box flex-view" v-if="commentData.length > 0">
                      <span :class="sortIndex === 0 ? 'tab-select' : ''" @click="sortCommentList('recent')">最新</span>
                      <div class="line"></div>
                      <span :class="sortIndex === 1 ? 'tab-select' : ''" @click="sortCommentList('hot')">热门</span>
                    </div>
                  </div>
                  <div class="comments-list">
                    <div class="comment-item" v-for="item in commentData">
                      <div class="flex-item flex-view">
                        <img :src="item.cover ? item.cover : AvatarIcon" class="avator" />
                        <div class="person">
                          <div class="name">{{ item.username }}</div>
                          <div class="time">{{ item.commentTime }}</div>
                        </div>
                        <div class="float-right">
                          <span @click="like(item.id)">推荐</span>
                          <span class="num">{{ item.likeCount }}</span>
                        </div>
                      </div>
                      <p class="comment-content">{{ item.content }}</p>
                    </div>
                    <div class="infinite-loading-container">
                      <div class="infinite-status-prompt" style="">
                        <div slot="no-results" class="no-results">
                          <div></div>
                          <p>没有更多了</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </a-tab-pane>
            </a-tabs>
          </div>
          <div class="main-side">
            <div class="recommend">
              <div class="title">相关推荐</div>
              <div class="things">
                <div class="thing-item" v-for="item in recommendData" @click="handleDetail(item)">
                  <div class="img-view"> <img :src="item.cover" /></div>
                  <div class="info-view">
                    <h3 class="thing-name">{{ item.title.substring(0, 12) }}</h3>
                    <span>
                      <span class="a-price-symbol"></span>
                      <span class="a-price">{{ item.age }}</span>&nbsp;
                    </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="recommend" style="margin-top: 20px">
              <div class="title">广告</div>
              <div class="things">
                <div class="thing-item thing-item" v-for="item in adData" @click="clickAd(item)">
                  <div class="img-view"> <img :src="item.cover" /></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>
<script setup>
  import { message } from 'ant-design-vue';
  import Header from '/@/views/index/components/header.vue';
  import Footer from '/@/views/index/components/footer.vue';
  import AddIcon from '/@/assets/images/add.svg';
  import WantIcon from '/@/assets/images/want-read-hover.png';
  import RecommendIcon from '/@/assets/images/recommend-hover.png';
  import ShareIcon from '/@/assets/images/share-icon.svg';
  import AvatarIcon from '/@/assets/images/avatar.jpg';

  import { Modal } from 'ant-design-vue';

  import { detailApi, listApi as listThingList, getRecommendApi, rateApi, listUserThingApi } from '/@/api/thing';
  import { listThingCommentsApi, createApi as createCommentApi, likeApi } from '/@/api/comment';
  import { listApi as listAdApi } from '/@/api/ad';
  import { wishApi } from '/@/api/thingWish';
  import { collectApi } from '/@/api/thingCollect';
  import { BASE_URL } from '/@/store/constants';
  import { useRoute, useRouter } from 'vue-router/dist/vue-router';
  import { useUserStore } from '/@/store';
  import { getFormatTime } from '/@/utils';
  import { createApi } from '../../api/order';

  const router = useRouter();
  const route = useRoute();
  const userStore = useUserStore();

  let thingId = ref('');
  let detailData = ref({});
  let selectTabIndex = ref('1');

  let commentData = ref([]); // 评论数据
  let recommendData = ref([]); // 推荐数据
  let adData = ref([]); // 广告数据
  let sortIndex = ref(0);
  let order = ref('recent'); // 默认排序最新

  let commentRef = ref();

  onMounted(() => {
    thingId.value = route.query.id.trim();
    getThingDetail();
    getRecommendThing();
    getCommentList();
    getAdList();
  });

  const getThingDetail = () => {
    detailApi({ id: thingId.value })
      .then((res) => {
        detailData.value = res.data;
        detailData.value.cover = BASE_URL + '/api/staticfiles/image/' + detailData.value.cover;
      })
      .catch((err) => {
        message.error('获取详情失败');
      });
  };
  const addToWish = () => {
    let userId = userStore.user_id;
    if (userId) {
      wishApi({ thingId: thingId.value, userId: userId })
        .then((res) => {
          message.success(res.msg);
          getThingDetail();
        })
        .catch((err) => {
          console.log('操作失败');
        });
    } else {
      message.warn('请先登录');
    }
  };
  const collect = () => {
    let userId = userStore.user_id;
    if (userId) {
      collectApi({ thingId: thingId.value, userId: userId })
        .then((res) => {
          message.success(res.msg);
          getThingDetail();
        })
        .catch((err) => {
          console.log('收藏失败');
        });
    } else {
      message.warn('请先登录');
    }
  };
  const share = () => {
    let content = '分享一个非常好玩的网站 ' + window.location.href;
    let shareHref = 'http://service.weibo.com/share/share.php?title=' + content;
    window.open(shareHref);
  };

  const handleOrder = (detailData) => {
    console.log(detailData);
    const userId = userStore.user_id;

    if (userId) {
      // 检查是否登记过
      listUserThingApi({ userId: userId })
          .then((res) => {
            console.log(res);
            if (res.data && res.data.length > 0) {
              let myThing = res.data[0];
              router.push({
                name: 'confirm',
                query: {
                  fromId: myThing.id,
                  id: detailData.id,
                  title: detailData.title,
                  cover: detailData.cover,
                  sex: detailData.sex,
                  age: detailData.age,
                },
              });
            }else {
              message.warn('请先登记个人信息！');
              router.push({ name: 'applyView' });
            }
          })
          .catch((err) => {
            console.log(err);
          });
    } else {
      message.warn('请先登录！');
    }
  };
  const getRecommendThing = () => {
    getRecommendApi({})
      .then((res) => {
        res.data.forEach((item, index) => {
          if (item.cover) {
            item.cover = BASE_URL + '/api/staticfiles/image/' + item.cover;
          }
        });
        console.log(res);
        recommendData.value = res.data.slice(0, 6);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const handleDetail = (item) => {
    // 跳转新页面
    let text = router.resolve({ name: 'detail', query: { id: item.id } });
    window.open(text.href, '_blank');
  };
  const clickRate = (num) => {
    console.log('num:' + num);
    rateApi({ thingId: thingId.value, rate: num })
      .then((res) => {
        message.success('操作成功！');
        getThingDetail();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const clickAd = (item) => {
    window.open(item.link, '_blank');
  };
  const sendComment = () => {
    console.log(commentRef.value);
    let text = commentRef.value.value.trim();
    console.log(text);
    if (text.length <= 0) {
      return;
    }
    commentRef.value.value = '';
    let userId = userStore.user_id;
    if (userId) {
      createCommentApi({ content: text, thingId: thingId.value, userId: userId })
        .then((res) => {
          getCommentList();
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      message.warn('请先登录！');
      router.push({ name: 'login' });
    }
  };
  const like = (commentId) => {
    likeApi({ id: commentId })
      .then((res) => {
        getCommentList();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getCommentList = () => {
    listThingCommentsApi({ thingId: thingId.value, order: order.value })
      .then((res) => {
        res.data.forEach((item) => {
          item.commentTime = getFormatTime(item.commentTime, true);
          if(item.cover){
            item.cover = BASE_URL + '/api/staticfiles/avatar/' + item.cover;
          }
        });
        commentData.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getAdList = () => {
    listAdApi({})
      .then((res) => {
        res.data.forEach((item, index) => {
          if (item.image) {
            item.cover = BASE_URL + '/api/staticfiles/image/' + item.image;
          }
        });
        adData.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const sortCommentList = (sortType) => {
    if (sortType === 'recent') {
      sortIndex.value = 0;
    } else {
      sortIndex.value = 1;
    }
    order.value = sortType;
    getCommentList();
  };
</script>
<style scoped lang="less">
  // 兼容
  @media screen and (max-width: 720px) {
    .detail-content {
      width: 90% !important;
    }
    .top-left-img {
      width: 99% !important;
    }
    .main-side {
      width: 100% !important;
    }
    .more {
      display: none !important;
    }
  }

  .detail {
    background: #ebf7f8;
    padding-top: 100px;
  }

  .detail-content {
    display: flex;
    flex-direction: column;
    width: 80%;
    margin: 0 auto;
    .detail-content-top {
      padding: 20px;
      box-sizing: border-box;
      background: #fff;
      border-radius: 2px;
      box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      gap: 20px;
      .top-left-img {
        width: 350px;
        aspect-ratio: 4/3;
        object-fit: cover;
        border-radius: 8px;
      }
      .top-right {
        display: flex;
        flex-direction: column;
        flex: 1;
        gap: 4px;
        .title {
          display: flex;
          flex-direction: row;
          align-items: center;
          gap: 10px;
          .tag {
            display: inline-block;
            padding: 0 6px;
            border-radius: 2px;
            font-size: 12px;
            font-weight: bold;
            height: 20px;
            line-height: 20px;
            color: #fff;
            background: #f64242;
          }
          .title-value {
            font-size: 30px;
            color: #111;
            font-weight: bold;
          }
        }
        .score {
          display: flex;
          flex-direction: row;
          align-items: center;
          gap: 16px;
          .pv {
            font-size: 12px;
            color: #555;
          }
        }
        .price {
          font-size: 14px;
          color: #333;
        }
        .meta {
          font-size: 14px;
          color: #333;
        }
        .actions {
          margin-top: auto;
          display: flex;
          flex-direction: row;
          align-items: center;
          gap: 16px;
          .buy-btn {
            background: #4684e2;
            cursor: pointer;
            display: block;
            border-radius: 4px;
            text-align: center;
            color: #fff;
            font-size: 14px;
            height: 36px;
            line-height: 36px;
            width: 110px;
            outline: none;
            border: none;
            margin-right: auto;
          }
          .more {
            display: flex;
            flex-direction: row;
            gap: 16px;
            .btn-like {
              white-space: nowrap;
              display: flex;
              flex-direction: row;
              cursor: pointer;
              img {
                width: 20px;
                height: 20px;
                object-fit: fill;
              }
              .label {
                font-size: 14px;
                color: #777;
              }
            }
          }
        }
      }
    }
  }

  .thing-content-view {
    margin-top: 40px;
    padding-bottom: 50px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 30px;
  }

  .main-content {
    flex: 1;
    min-width: 300px;
    padding: 0 16px;

    background: #fff;
    border-radius: 2px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);

    .text {
      color: #484848;
      font-size: 16px;
      line-height: 26px;
      padding-left: 12px;
      margin: 11px 0;
      white-space: pre-wrap;
    }

    .thing-comment {
      .title {
        font-weight: 600;
        font-size: 14px;
        line-height: 22px;
        height: 22px;
        color: #152844;
        margin: 24px 0 12px;
      }

      .publish {
        align-items: center;

        .mine-img {
          -webkit-box-flex: 0;
          -ms-flex: 0 0 40px;
          flex: 0 0 40px;
          margin-right: 12px;
          border-radius: 50%;
          width: 40px;
          height: 40px;
        }

        .content-input {
          flex: 1;
          background: #f6f9fb;
          border-radius: 4px;
          min-width: 100px;
          height: 32px;
          line-height: 32px;
          color: #484848;
          padding: 5px 12px;
          white-space: nowrap;
          outline: none;
          border: 0px;
        }

        .send-btn {
          margin-left: 10px;
          width: 80px;
          background: #4684e2;
          border-radius: 4px;
          color: #fff;
          font-size: 14px;
          text-align: center;
          height: 32px;
          line-height: 32px;
          outline: none;
          border: 0px;
          cursor: pointer;
        }
      }

      .tab-view {
        -webkit-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
        font-size: 14px;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        margin: 24px 0;

        .count-text {
          color: #484848;
          float: left;
        }

        .tab-box {
          color: #5f77a6;
          -webkit-box-align: center;
          -ms-flex-align: center;
          align-items: center;

          .tab-select {
            color: #152844;
          }

          span {
            cursor: pointer;
          }
        }

        .line {
          width: 1px;
          height: 12px;
          margin: 0 12px;
          background: #cedce4;
        }
      }
    }

    .comments-list {
      .comment-item {
        .flex-item {
          -webkit-box-align: center;
          -ms-flex-align: center;
          align-items: center;
          padding-top: 16px;

          .avator {
            -webkit-box-flex: 0;
            -ms-flex: 0 0 40px;
            flex: 0 0 40px;
            width: 40px;
            height: 40px;
            margin-right: 12px;
            border-radius: 20px;
            cursor: pointer;
          }

          .person {
            -webkit-box-flex: 1;
            -ms-flex: 1;
            flex: 1;
          }

          .name {
            color: #152844;
            font-weight: 600;
            font-size: 14px;
            line-height: 22px;
            height: 22px;
            cursor: pointer;
          }

          .time {
            color: #5f77a6;
            font-size: 12px;
            line-height: 16px;
            height: 16px;
            margin-top: 2px;
          }

          .float-right {
            color: #4684e2;
            font-size: 14px;
            float: right;

            span {
              margin-left: 19px;
              cursor: pointer;
            }

            .num {
              color: #152844;
              margin-left: 6px;
              cursor: auto;
            }
          }
        }
      }
    }

    .comment-content {
      margin-top: 8px;
      color: #484848;
      font-size: 14px;
      line-height: 22px;
      padding-bottom: 16px;
      border-bottom: 1px dashed #cedce4;
      margin-left: 52px;
      overflow: hidden;
      word-break: break-word;
    }
  }

  .main-side {
    width: 300px;

    .recommend {
      background: #fff;
      border-radius: 2px;
      box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);

      .title {
        font-weight: 600;
        font-size: 16px;
        height: 48px;
        line-height: 48px;
        padding-left: 16px;
        color: #152844;

        border-bottom: 1px solid #eee;
      }

      .things {
        display: flex;
        flex-direction: column;
        gap: 16px;
        padding: 20px 0;

        .thing-item {
          width: 90%;
          margin: 0px auto;
          position: relative;
          cursor: pointer;

          .img-view {
            width: 100%;
            img {
              border-radius: 8px;
              width: 100%;
              aspect-ratio: 5/3;
              background-size: cover;
              object-fit: cover;
            }
          }

          .info-view {
            //background: #f6f9fb;
            overflow: hidden;
            .thing-name {
              margin-top: 12px;
              color: #0f1111;
              font-weight: bold;
            }

            .price {
              color: #ff7b31;
              font-size: 20px;
              line-height: 20px;
              margin-top: 4px;
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }
          }
        }
      }
    }
  }

  .flex-view {
    display: flex;
  }
</style>
