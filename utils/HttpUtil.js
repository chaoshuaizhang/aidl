/**
 * Created by changePosition on 2018/11/29.
 */
import {SERVER_URL} from "../Constants";
export default {
    fetchPostBody,
    fetchPostFormData,
    fetchGet
};


const httpParametersFormData = (method, formData) => ({
    method: method,
    headers: {},
    body: formData
});

const httpParametersBody = (method, body = {}) => ({
    method: method,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(body),
});
const httpParametersGet = (method, body = {}) => ({
    method: method,
    headers: {
    }
});

function fetchPostFormData(url, formData) {
    return fetchUrlPostFormData(url, 'POST', formData);
}
function fetchPostBody(url, bodyJson) {
    return fetchUrlPostBody(url, 'POST', bodyJson);
}
function fetchGet(url) {
    return fetchUrlGet(url, 'GET');
}

function fetchUrlGet(url, method) {

    return new Promise(function (resolve, reject) {
        fetch(SERVER_URL + "/" + url, httpParametersGet(method))
            .then((response) => {
                console.log('数据返回：' + JSON.stringify(response))
                resolve(response);
                // if (response.ok) {
                //     resolve(response);
                // } else {
                //     reject(response);
                // }
            }).catch((err) => {
            reject('网络异常，请检查网络后再试。');
        })
    });
}
function fetchUrlPostBody(url, method, bodyJson) {

    return new Promise(function (resolve, reject) {
        fetch(SERVER_URL + "/" + url, httpParametersBody(method, bodyJson))
            .then((response) => {
                console.log('数据返回：' + JSON.stringify(response))
                resolve(response);
                // if (response.ok) {
                //     resolve(response);
                // } else {
                //     reject(response);
                // }
            }).catch((err) => {
            reject('网络异常，请检查网络后再试。');
        })
    });
}

function fetchUrlPostFormData(url, method, formData) {

    return new Promise(function (resolve, reject) {
        fetch(SERVER_URL + "/" + url, {
            method: method,
            headers: {},
            body: formData
        }).then((response) => {
                console.log('数据返回：' + JSON.stringify(response))
                resolve(response);
                // if (response.ok) {
                //     resolve(response);
                // } else {
                //     reject(response);
                // }
            }).catch((err) => {
            reject('网络异常，请检查网络后再试。');
        })
    });
}

