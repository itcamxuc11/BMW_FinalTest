$(document).ready(function () {
    $(".edit-exam").click(function () {
        var maDe = $(this).data("id");
        var monHoc = $(this).data("sub");
        window.location.href = "SuaDeThi?maDe=" + maDe +"&monHoc="+monHoc+ "&t=" + Math.random();
    })
})

