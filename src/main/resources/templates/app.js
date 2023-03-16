function getAllSong() {
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/admins",
        success: function (data) {
            displaySong(data)
        }
    });
}

function getAllSongPage(page) {
    $("#showTable").show()
    $.ajax({
        headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "GET",
        url: "http://localhost:8080/songs/page?page=" + page + "&size=3",
        success: function (data) {
            console.log(data.content)
            displaySong(data)
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
    let content = `<table class="table table-striped">
            <tr>
                <th colspan="11" style="text-align: center; font-size: xx-large">LIST SONG</th>
            </tr>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price($)</th>
                <th>dateCreate</th>
                <th>Singer</th>
                <th>Album</th>
                <th>Avatar</th>
                <th>File Mp3</th>
                <th colspan="2">Action</th>
            </tr>`;
    for (let i = 0; i < data.content.length; i++) {
        content +=
            `<tr>
                <td scope="row">${data.content[i].id}</td>
                <td scope="row">${data.content[i].name}</td>
                <td scope="row">${data.content[i].price}"</td>
                <td scope="row"">${data.content[i].dateCreateSong}"</td>
                <td scope="row"">${data.content[i].singer}""</td>
                <td scope="row"">${data.content[i].album}"</td>
                <td>
                    <img style="height: 70px; width: 70px" src="${data.content[i].avatar}">
                </td>
                <td><audio controls>
                    <source src="${data.content[i].fileMp3}">
                </audio>
                </td>
                <td>
                    <button class="btn btn-primary" onclick="update(data.content[i].id)"">Update</button>
                </td>
                <td>
                    <button class="btn btn-danger" onclick="deleteSong(data.content[i].id)">Delete</button>
                </td>
            </tr>`
    }
    content += '</table>'
    console.log(content)
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

function update(id) {
    let name = $("#name").val()
    let price = $("#price").val()
    let address = $("#address").val()
    let description = $("#description").val()
    let singer = $("#singer").val()
    let album = $("#album").val()
    let avatar = $("#avatar").val()
    let fileMp3 = $("#fileMp3").val()
    let newSong = {
        // id: sessionStorage.getItem("update"),
        id: id,
        name: name,
        price: price,
        address: address,
        description: description,
        singer: singer,
        album: album,
        avatar: avatar,
        fileMp3: fileMp3
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
        type: "PUT",
        url: "http://localhost:8080/songs/" + sessionStorage.getItem("update"),
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
                getAllSong()
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
    let price = $("#price").val()
    let address = $("#address").val()
    let description = $("#description").val()
    let singer = $("#singer").val()
    let album = $("#album").val()
    let avatar = $("#avatar").val()
    let fileMp3 = $("#fileMp3").val()
    let newSong = {
        id: sessionStorage.getItem("update"),
        name: name,
        price: price,
        address: address,
        description: description,
        singer: singer,
        album: album,
        avatar: "",
        fileMp3: ""
    }
    let formData = new FormData();
    if ($('#avatar')[0].files.length > 0) {
        formData.append("file", $('#avatar')[0].files[0]);
    }
    if ($('#fileMp3')[0].files.length > 0) {
        $.each($('#fileMp3')[0].files, function(i, file) {
            formData.append("file", file);
        });
    }
    formData.append("song", new Blob([JSON.stringify(newSong)], {type: 'application/json'}));
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // Authorization: "Bearer " + sessionStorage.getItem("token"),
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
