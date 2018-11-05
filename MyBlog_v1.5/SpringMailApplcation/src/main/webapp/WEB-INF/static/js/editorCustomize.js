
var E = window.wangEditor


var editor = new E('#editor')
//自定义菜单栏
editor.customConfig.menus = [
    'head',  // 标题
    'bold',  // 粗体
    'fontSize',  // 字号
    'foreColor',  // 文字颜色
    'backColor',  // 背景颜色
    'link',  // 插入链接
    'list',  // 列表
    'quote',  // 引用
    'emoticon',  // 表情
    'image',  // 插入图片
    'table', // 表格
]

//上传图片的服务器地址
editor.customConfig.uploadImgServer = '/upload'
// 将图片大小限制为 3M
editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
// 限制一次最多上传 5 张图片
editor.customConfig.uploadImgMaxLength = 5;
//不过滤复制过来的文字样式
editor.customConfig.pasteFilterStyle = false;

/* 定义上传图片的默认名字 */
editor.customConfig.uploadFileName = 'myFileName';



// 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
editor.customConfig.emotions = [
    {
        // tab 的标题
        title: '默认',
        // type -> 'emoji' / 'image'
        type: 'image',
        // content -> 数组
        content: [
            {
                alt: '[坏笑]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4d/2018new_huaixiao_org.png'
            },
            {
                alt: '[笑哭]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4a/2018new_xiaoku_thumb.png'
            },
            {
                alt: '[馋嘴]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/fa/2018new_chanzui_thumb.png'
            },
            {
                alt: '[拜拜]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/fd/2018new_baibai_thumb.png'
            },
            {
                alt: '[右哼哼]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c1/2018new_youhengheng_thumb.png'
            },
            {
                alt: '[左哼哼]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/43/2018new_zuohengheng_thumb.png'
            },
            {
                alt: '[怒骂]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/fa/2018new_chanzui_thumb.png'
            },
            {
                alt: '[顶]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ae/2018new_ding_org.png'
            },
            {
                alt: '[微笑]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e3/2018new_weixioa02_org.png'
            },
            {
                alt: '[亲亲]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/2c/2018new_qinqin_thumb.png'
            },
            {
                alt: '[嘘]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b0/2018new_xu_org.png'
            },
            {
                alt: '[吃惊]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/49/2018new_chijing_org.png'
            },
            {
                alt: '[污]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/10/2018new_wu_thumb.png'
            },
            {
                alt: '[鼓掌]',
                src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/2018new_guzhang_thumb.png'
            },
            {
                alt: '[互粉]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/86/2018new_hufen02_org.png"
            },
            {
                alt: '[悲伤]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ee/2018new_beishang_org.png"
            },
            {
                alt: '[泪]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/2018new_leimu_org.png"
            },
            {
                alt: '[闭嘴]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/62/2018new_bizui_org.png"
            },
            {
                alt: '[握手]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e9/2018new_woshou_thumb.png"
            },
            {
                alt: '[加油]',
                src: "http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9f/2018new_jiayou_thumb.png"
            }

        ]
    }
]
editor.create();


