function getAllSong() {
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/songs",
        success: function (data) {
            displaySong(data)
        }
    });
}

function getAllSongPage(page) {
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/songs/page?page=" + page + "&size=3",
        success: function (data) {
            displayCustomer(data.content)
            displayPage(data)
            //điều kiện bỏ nút previous
            if (data.pageable.pageNumber === 0) {
                document.getElementById("backup").hidden = true
            }
            //điều kiện bỏ nút next
            if (data.pageable.pageNumber + 1 === data.totalPages) {
                document.getElementById("next").hidden = true
            }
        }
    });
}

function displaySong(data) {
    let content = `<thead>
            <tr>
                <th colspan="11" style="text-align: center; font-size: xx-large">LIST SONG</th>
            </tr>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price($)</th>
                <th>Description</th>
                <th>Singer</th>
                <th>Album</th>
                <th>Avatar</th>
                <th>File Mp3</th>
                <th colspan="2">Action</th>
            </tr>
            </thead>`;
    for (let i = 0; i < data.length; i++) {
        content += `<tbody>
            <tr>
                <td ${data[i].id}</td>
                <td ${data[i].name}</td>
                <td ${data[i].price}"></td>
                <td ${data[i].description}"></td>
                <td ${data[i].singer}"></td>
                <td ${data[i].album}"></td>
                <td <img style="height: 70px; width: 70px" src="${data[i].avatar}" alt=""></td>
                <td><audio controls><source src="${data[i].fileMp3}"></td>
            </tbody>`;
    }
    content += '</table>'
    document.getElementById('list_customer').innerHTML = content;
}

//hàm hiển thị phần chuyển page
function displayPage(data) {
    let content = `<button class="btn btn-primary" id="backup" onclick="isPrevious(${data.pageable.pageNumber})">Previous</button>
    <span>${data.pageable.pageNumber + 1} | ${data.totalPages}</span>
    <button class="btn btn-primary" id="next" onclick="isNext(${data.pageable.pageNumber})">Next</button>`
    document.getElementById('page').innerHTML = content;
}

//hàm lùi page
function isPrevious(pageNumber) {
    getAllSongPage(pageNumber - 1)
}

//hàm tiến page
function isNext(pageNumber) {
    getAllSongPage(pageNumber + 1)
}

function back() {
    $("#form").hide()
    $("#list_customer").show()
    $("#btn-create").show()
    $("#div-search").show()
    event.preventDefault();
}

function updateForm(id) {
    sessionStorage.setItem("update", id)
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/songs/" + id,
        success: function (data) {
            $("#name").val(data.name)
            $("#price").val(data.price)
            $("#description").val(data.description)
            $("#singer").val(data.singer)
            $("#album").val(data.album)
            $("#age").val(data.age)
            $("#address").val(data.address)
            $("#list_customer").hide()
            $("#btn-create").hide()
            $("#div-search").hide()
            $("#title").html("Update form").css("text-align", "center")
            $("#form").show().css("width", "500px").css("margin", "auto")
            $("#submit").attr("onclick", "update()").val("Update").css("width", "100px")
        }
    });
}

function update() {
    let name = $("#name").val()
    let age = $("#age").val()
    let address = $("#address").val()
    let newCustomer = {
        id: sessionStorage.getItem("update"),
        name: name,
        age: age,
        address: address
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "PUT",
        url: "http://localhost:8080/customers/" + sessionStorage.getItem("update"),
        data: JSON.stringify(newCustomer),
        success: function (data) {
            getAllSong()
            if (data.name != null) {
                alert("Update successfully!")
                $("#form").hide()
                $("#list_customer").show()
                $("#btn-create").show()
                $("#div-search").show()
            }
        }
    })
    event.preventDefault();
}

function deleteSong(id) {
    if (confirm("Are you sure you want to delete?")) {
        $.ajax({
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("token"),
            },
            type: "DELETE",
            url: "http://localhost:8080/songs/" + id,
            success: function (data) {
                getAllCustomer()
                if (data !== "") {
                    alert("Delete successfully!")
                }
            }
        });
    }
}

function createForm() {
    $(".form-control").val("")
    $("#list_customer").hide()
    $("#btn-create").hide()
    $("#div-search").hide()
    $("#title").html("Create form").css("text-align", "center")
    $("#form").show().css("width", "500px").css("margin", "auto")
    $("#submit").attr("onclick", "create()").val("Create").css("width", "100px")
}

function create() {
    let name = $("#name").val()
    let age = $("#age").val()
    let address = $("#address").val()
    let newSong = {
        name: name,
        age: age,
        address: address,
        avatar: ""
    }
    let formData = new FormData();
    formData.append("file", $('#file')[0].files[0])
    formData.append("song", new Blob([JSON.stringify(newSong)]
        , {type: 'application/json'}))
    $.ajax({
        headers: {
            // 'Accept': 'application/json',
            // 'Content-Type': 'application/json',
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        contentType: false,
        processData: false,
        type: "POST",
        url: "http://localhost:8080/songs/upload",
        data: formData,
        success: function (data) {
            getAllSong()
            if (data.name != null) {
                alert("Create successfully!")
                $("#form").hide()
                $("#list_customer").show()
                $("#btn-create").show()
                $("#div-search").show()
            }
        }
    })
    event.preventDefault();
}

function searchByName() {
    let search = $("#search").val()
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/songs/search?search=" + search,
        success: function (data) {
            displaySong(data)
        }
    })
}
