# datacenter

##安装node环境
https://nodejs.org/en/download/ 下载自己系统版本安装包
##安装npm
npm install -g cnpm
##安装webpack
npm install -g webpack
##安装vue-cli脚手架
npm install -g vue-cli
##功能
- 我的企业
  - 详情
  - 任务列表
  - 更多
  - 分页
- 数据构造
  - 创建企业
  - 创建内部群聊
  - 创建外部群聊
  - 发送消息
  - 添加好友
  - 设置应用
- 任务列表
  - 任务详情
  - 分页
- 高级模式
  - 项目文件管理
  - 编辑器
  - API管理
- Q&A文档
- 操作说明
- API文档
- 联系我们

## 文件结构
```shell
.
├── build  项目构建配置
├── node_modules 引入的包文件,可查看所有组件的属性
├── config  开发相关配置
├── public  打包所需静态资源
└── src
    ├── api  ajax请求以及定义的规则
        ├── mycorp.js  所有与后台交互的请求接口
        └── rule.js 所有的规则定义
    └── assets  项目静态资源
        ├── icons  自定义图标资源
        └── images  图片资源
    ├── components  业务组件
    ├── config  项目运行配置
    ├── directive  自定义指令
    ├── libs  封装工具函数
        ├── axios.js  处理所有的请求逻辑
        └── util.js 处理登录逻辑,包括登录态
    ├── locale  多语言文件
    ├── mock  mock模拟数据
    ├── router  路由配置
    ├── store  Vuex配置
    ├── view  页面文件
        ├── components   业务组件(已弃用)
        └── corp 所有页面代码(展示的页面)
            ├── answers.vue  Q&A页面
            ├── api.vue API页面
            ├── mycorp.vue 我的企业页面
            ├── create.vue 数据构造页面
            ├── task_list.vue 任务列表页面
            ├── taskinfo.vue 任务详情页面
            ├── person.vue 联系我们页面
            ├── instrcutions.vue 操作说明页面
            └──  editor.vue 高级模式页面

    └── tests  测试相关
```
## 参考文档
- [iview](https://www.iviewui.com/docs/guide/install)
- [Vue](https://github.com/vuejs/vue)
- [vue2-ace-editor](https://github.com/chairuosen/vue2-ace-editor)

## 重要代码说明
### routers.js代码说明
{
    path: '/', //页面路径
    name: '_datacenter', //页面名称,注意:name为唯一标志,不允许重复,否则报duplicate name错误
    redirect: '/home', //进入页面是否重定向
    component: Main, //组件名,对应Main.vue
    meta: {
      hideInMenu: true, //是否在左侧菜单栏sidemenu隐藏入口
      notCache: true //是否缓存
    },
    children: [ //该组件的子组件,声明多个children可实现tree效果
      {
        path: '/home', //路径.若需要使用动态路由保证刷新页面页面数据不丢失,在使用this.$router.push方式进行跳转时,可在路径中使用path: ‘/yourpath/:yourdata1/:yourdata2’ 来实现
        name: 'home', //名称
        meta: {
          hideInMenu: true, //是否显示
          title: '首页', //标题
          notCache: true //是否缓存
        },
        component: () => import('@/view/corp/mycorp.vue') //指明对应的vue组件
      }
    ]
  },

  ### mycorp.vue代码说明
  #### vue代码框架
  <template> //html内容
  </template>
  <script>
  import //所有需要导入的方法和组件
  export default {
    name: 'mycorp' //组件名称
    components: {Tables} //导入的需要声明的组件
    props:{} //组件上注册的自定义特性,可实现父子组件之间的传值.例如table组件
    data() {} //所有的数据内容
    methods:{} //所有定义的方法
    computed(){} //计算属性,可对某一属性持续监听并可实时响应
    watch(){} //监听属性
    beforeCreated(){} //vue实例创建前的钩子事件
    created(){} //创建完成后的钩子事件
    beforMounted () {} //编译完html后挂载到dom时触发的钩子
    mounted(){} //将编译好的html挂载到页面后执行的钩子
    beforeUpdate(){} //更新之前的钩子
    update(){} //更新后的钩子
    beforeDestroy(){} //销毁实例前的钩子
    destroyed(){} //销毁实例后的钩子
  }
  </script>
  <style>//css指定更加灵活的参数,布局
  </style>
  ### 代码常用函数说明
  #### render函数常用结构
  render: (h, params) => {
    return h('组件类型',{
    	props:{
    		// 组件属性
    		},
    	style:{
    		// 布局
    		},
    	on: {
    		// 响应
    	}
    	},’名称‘)
  } //使用render渲染时参考html结构

  ### create.vue模块说明
  #### 构造流程
  - 获得数据,初始化页面
  beforeCreate () {
    - getTaskParams() 中获取支持的创建数据内容(创建企业、创建内部群聊等),并获取所有的组件参数.
    - getMyCorp() 中获取当前账户的所有可用企业.
    以上请求并发执行,获取数据后html中获得数据,开始编译,编译完成后挂载至dom,此时进入默认页面创建企业.
  }
  - 动态渲染页面
    - 显示创建内容,其他页面内容根据创建内容进行显示
    - v-for遍历所有的组件参数,目前的组件内容可以区分为带规则的input、无规则input、需要拉取数据的单选select、需要动态响应拉取参数的单选select、无需拉取数据的带参select、需要拉取数据的多选select.
    - 考虑以上两点,则:一、在渲染时判断当前创建内容类型,根据创建内容类型,选择status匹配的组件;二、判断需要触发方法拉取数据的组件;三、判断选择动态响应的组件
    - 代码示例:
      - select+item.params+externalContactType !== 'externalcontactco_corpidwx' && select+item.params+externalContactType !== 'externalcontactco_corpidww 此处select+item.params+externalContactType表达的含义是创建外部群聊中的互联企业corpid在外部联系人类型为微信联系人、外部联系人类型时,该input框不显示
      - <FormItem
        v-else-if="item.status === select && item.inputtype === 'single_select' && rules !== '' && (item.params === 'corpname' )" //这里的条件判断表示对当前页面的企业名称单选框进行渲染(注意:企业名称单选框存在在多个创建页面)
        :key="index"
        :label="item.index" //名称
        :prop="'items.' + index + '.value'"
        :rules="rules[item.ruleid]" //校验规则
      >
        <Select v-model="item.value" @on-change="handleSelectCorp(item.value)" filterable style="width: 420px"> //标明选中的值给到item.value并触发handleSelectCorp(item.value),单选框可搜索
          <Option
            v-for="(item,index) in formDynamic.corpdata" //单选框中的内容列表
            :value=item.corpname>{{item.corpname}}</Option>
        </Select>
      </FormItem>
     - 完成页面渲染后提交数据的逻辑
       - 将创建内容的所有数据取出,若是;分割的根据get请求或post请求分别进行处理,多选的数据保存为数组类型,也需要单独进行处理.
       - 根据当前的创建数据类型调用不同的请求.