var userMenuData = [
    {
        "id": "userInfo", iconCls: "fa fa-send-o", text: "个人信息", children: [
            {"id": "updatePassword", iconCls: "fa fa-send-o", text: "修改密码", url: "/user/updatePassword"},
            {"id": "checkInfo", iconCls: "fa fa-send-o", text: "查看个人信息", url: "/user/getUserInfo"},
            {"id": "updateInfo", iconCls: "fa fa-send-o", text: "修改个人信息", url: "/user/updateUserInfo"},
        ]
    },
];

var workerMenuData = [
    {
        "id": "userInfo", iconCls: "fa fa-send-o", text: "个人信息", children: [
            {"id": "updatePassword", iconCls: "fa fa-send-o", text: "修改密码", url: "/worker/updatePassword"},
            {"id": "checkInfo", iconCls: "fa fa-send-o", text: "查看信息", url: "/worker/getUserInfo"},
            {"id": "updateInfo", iconCls: "fa fa-send-o", text: "修改信息", url: "/worker/updateUserInfo"},
        ],
    },
    {
        "id": "itemControl", iconCls: "fa fa-send-o", text: "项目管理", children: [
            {"id": "createItem", iconCls: "fa fa-send-o", text: "新建项目", url: "/worker/createItem"},
            {"id": "getItems", iconCls: "fa fa-send-o", text: "查看项目", url: "/worker/getItems?page=1"},
        ],
    },
];