let filterForm;
const mealAjaxUrl = "meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

function updateTable() {
    if (ctx.useFilter) {
        $.ajax({
            type: "GET",
            url: ctx.ajaxUrl + 'filter',
            data: filterForm.serialize()
        }).done(function (data) {
            updateTableByData(data);
        });
        return true;
    }

    $.get(ctx.ajaxUrl, function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}

function cleanFilter() {
    filterForm[0].clean();
    updateTable();
}

// $(document).ready(function () {
$(function () {
    filterForm = $('#filterDetails');
    $('#datetimepicker').datetimepicker({
        format: 'd.m.Y H:i',
        closeOnDateSelect: true,
    });
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});