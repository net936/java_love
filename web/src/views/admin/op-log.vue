<template>
  <div>
    <!--页面区域-->
    <div class="page-view">
      <div class="table-operations">
        <a-space>
          <a-button type="primary" @click="clickClear()">清空</a-button>
        </a-space>
      </div>
      <a-table
        size="middle"
        rowKey="id"
        :loading="data.loading"
        :columns="columns"
        :data-source="data.dataList"
        :scroll="{ x: 'max-content' }"
        :pagination="{
          size: 'default',
          current: data.page,
          pageSize: data.pageSize,
          onChange: (current) => (data.page = current),
          showSizeChanger: false,
          showTotal: (total) => `共${total}条数据`,
        }"
      >
        <template #bodyCell="{ text, record, index, column }">
          <template v-if="column.key === 'operation'">
            <span>
              <a @click="handleEdit(record)">编辑</a>
              <a-divider type="vertical" />
              <a-popconfirm title="确定删除?" ok-text="是" cancel-text="否" @confirm="confirmDelete(record)">
                <a href="#">删除</a>
              </a-popconfirm>
            </span>
          </template>
        </template>
      </a-table>
    </div>
  </div>
</template>

<script setup>
  import { message } from 'ant-design-vue';
  import { clearOpLogListApi, listOpLogListApi } from '/@/api/log';

  const columns = reactive([
    {
      title: '序号',
      dataIndex: 'index',
      key: 'index',
      align: 'center',
    },
    {
      title: '请求方式',
      dataIndex: 'reMethod',
      key: 'reMethod',
      align: 'center',
    },
    {
      title: '请求URL',
      dataIndex: 'reUrl',
      key: 'reUrl',
      align: 'center',
    },
    {
      title: '操作IP',
      dataIndex: 'reIp',
      key: 'reIp',
      align: 'center',
    },
    {
      title: '操作时间',
      dataIndex: 'reTime',
      key: 'reTime',
      align: 'center',
    },
    {
      title: '耗时',
      dataIndex: 'accessTime',
      key: 'accessTime',
      align: 'center',
      customRender: ({ text, record, index, column }) => text + 'ms',
    },
  ]);

  // 页面数据
  const data = reactive({
    dataList: [],
    loading: false,
    keyword: '',
    selectedRowKeys: [],
    pageSize: 10,
    page: 1,
  });

  onMounted(() => {
    getDataList();
  });

  const getDataList = () => {
    data.loading = true;
    listOpLogListApi({
      keyword: data.keyword,
    })
      .then((res) => {
        data.loading = false;
        console.log(res);
        res.data.forEach((item, index) => {
          item.index = index + 1;
        });
        data.dataList = res.data;
      })
      .catch((err) => {
        data.loading = false;
        console.log(err);
      });
  };

  const clickClear = () => {
    data.loading = true;
    clearOpLogListApi({})
      .then((res) => {
        data.loading = false;
        message.success('操作成功');
        data.dataList = []
      })
      .catch((err) => {
        data.loading = false;
        console.log(err);
      });
  };

  const rowSelection = ref({
    onChange: (selectedRowKeys, selectedRows) => {
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
      data.selectedRowKeys = selectedRowKeys;
    },
  });
</script>

<style scoped lang="less">
  .page-view {
    min-height: 100%;
    background: #fff;
    padding: 24px;
    display: flex;
    flex-direction: column;
  }

  .table-operations {
    margin-bottom: 16px;
    text-align: right;
  }

  .table-operations > button {
    margin-right: 8px;
  }
</style>
