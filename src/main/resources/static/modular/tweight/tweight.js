var TWeight = {
    tableId: "#grid-table1",
    pagerId: "#grid-pager1",
    table: null,
    domain: "accidentLevel"
};

/**
 * jqGrid初始化参数
 */
TWeight.initOptions = function () {
    var options = {
        url : "/tweight/detail/grid",
        autowidth:true,
        postData : {
            date : $("#date").val()
        },
        rowNum:50,
        colNames: ['物料','物料重量','时间'],
        colModel: [
            {name: 'fmaterial', index: 'fmaterial', width: 150},
            {name: 'fweight', index: 'fweight', width: 150},
            {name: 'fdatetime', index: 'fdatetime', width: 150, editable: false,formatter: function (cellvar, options, rowObject) {
                    if (cellvar == "" || cellvar == undefined) {
                        return "";
                    }
                    var da = new Date(cellvar);
                    return dateFtt("yyyy-MM-dd hh:mm:ss", da);
                }},
            // {name: 'operations', index: 'operations', width: 80, sortable: false, formatter: function (cellValue, options, rowObject) {
            //
            //     var id = "'"+rowObject["id"]+"'";
            //     var str = "";
            //     // str += '<input type="button" class=" btn btn-sm btn-warning"  value="删  除" onclick="TWeight.delete(' + id + ')"/>&nbsp;';
            //     // str += '<input type="button" class=" btn btn-sm btn-info"  value="编辑" onclick="TWeight.modify(' + id + ')"/>&nbsp;';
            //     // str += '<input type="button" class=" btn btn-sm btn-danger"  value="删除" onclick="TWeight.delete(' + id + ')"/>';
            //     return str;
            // }}
        ]
    };
    return options;
};

/**
 * 根据关键词搜索
 */
TWeight.search = function () {
    var searchParam = {};
    searchParam.date = $("#date").val();
    TWeight.table.reload(searchParam);
};

/**
 * 重置搜索
 */
TWeight.resetSearch = function () {
    window.location.href = "/tweight/list";
};

/**
 *新增
 */
TWeight.create = function () {
    $("#createModal").modal();
}
TWeight.insert = function () {
    var accidentLevel = getFormJson($("#create-form"));
    $.ajax({
        url: "/tweight/insert",
        type: 'POST',
        data: JSON.stringify(accidentLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#createModal").modal("hide");
                success("保存成功");
                TWeight.search();
                $("#create-form")[0].reset();
            }
        }
    })
}

/**
 *编辑
 */
TWeight.modify = function (id) {
    $.ajax({
        url: "/tweight/get?id=" + id,
        type: 'GET',
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                var accidentLevel = r.obj;
                var form = $("#modify-form");
                form.find("input[name='name']").val(accidentLevel.name);
                form.find("input[name='id']").val(accidentLevel.id);
                $("#modifyModal").modal();
            }
        }
    })
    $("#modifyModal").modal();
}
TWeight.update = function () {
    var accidentLevel = getFormJson($("#modify-form"));
    $.ajax({
        url: "/tweight/update",
        type: 'POST',
        data: JSON.stringify(accidentLevel),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $("#modifyModal").modal("hide");
                success("编辑成功");
                TWeight.search();
                $("#modify-form")[0].reset();
            }
        }
    })
}

/**
 * 删除
 *
 * @param id    userId
 */
TWeight.delete = function del(id) {
    warning("确定删除吗", "", function () {
        $.get("/tweight/delete?id=" + id, function () {
            success("成功删除");
            TWeight.search();
        });
    })
};

    function dateFtt(fmt,date)
    { //author: meizz
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

$(function() {
    // $('.chosen-select').chosen({width: "100%"});
    var jqGrid = new JqGrid("#grid-table1", "#grid-pager1", TWeight.initOptions());
    TWeight.table = jqGrid.init();

});