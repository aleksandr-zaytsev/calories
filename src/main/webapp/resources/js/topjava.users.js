const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    useFilter: false
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
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
                    "asc"
                ]
            ]
        })
    );
});

function changeEnabled(checkbox) {
    $.ajax({
        type: "PATCH",
        url: ctx.ajaxUrl + $(checkbox).closest('tr').attr("id") + '/enabled?enabled=' + checkbox.checked
    })
        .done(function () {
            successNoty("User " + (checkbox.checked ? 'enabled' : 'disabled'));
        })
        .fail(function () {
            checkbox.checked = !checkbox.checked;
        })
        .always(function () {
            $(checkbox).closest('tr').attr("data-user-enabled", checkbox.checked);
            refreshCSS();
        });
}

function refreshCSS() {
    let links = document.getElementsByTagName('link');
    for (let i = 0; i < links.length; i++) {
        if (links[i].getAttribute('rel') === 'stylesheet') {
            let href = links[i].getAttribute('href')
                .split('?')[0];

            let newHref = href + '?version='
                + new Date().getMilliseconds();

            links[i].setAttribute('href', newHref);
        }
    }
}