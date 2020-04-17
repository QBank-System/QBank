window.onload = function () {
    const user = sessionStorage.getItem("QbankUser");
    if (user !== null) {
        window.location.href = "/QBank/main";
    }
};

function init() {
    const work_number = document.getElementById("user_work_number").value;
    const password = document.getElementById("user_password").value;
    if (work_number === null) {
        alert("请输入完整的职工号")
    } else if (password === null) {
        alert("请输入完整的密码")
    } else {
        const url = "/QBank/login/login";
        const data = {"user_work_number": work_number, "user_password": sha1(password)};
        $.ajax({
            url: url,
            dataType: "json",
            async: true,
            data: data,
            type: "POST",
            success: function (data) {
                alert(data["msg"]);
                if (0 === data["code"]) {
                    //TODO 加密
                    sessionStorage.setItem("QbankUser", JSON.stringify(data["data"]));
                    window.location.href = data["url"];
                }
            }
        });
    }
}