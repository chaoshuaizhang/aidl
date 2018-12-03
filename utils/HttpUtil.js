/**
 * Created by changePosition on 2018/11/29.
 */
export const ROOT_URL = 'http://172.16.100.158:8080/pdaware';

export default {
    fetchGet,
    fetchPost,
    fetchPut,
    ROOT_URL,
};

const httpParameters = (method) => ({
    method: method,
    headers: {
        // 'Accept': 'application/json',
        // 'Content-Type': 'application/json',
    },
});

const httpParametersGet = (method, formData) => ({
    method: method,
    headers: {},
    body: formData
});

const httpParametersBody = (method, body = {}) => ({
    method: method,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
});

function fetchGet(url, formData) {
    return fetchUrlFormData(url, 'GET', formData);
}

function fetchPost(url, body) {
    return fetchUrlPost(url, 'POST', body);
}

function fetchPut(url, body) {
    return fetchUrlPost(url, 'PUT', body);
}

function fetchUrlPost(url, method, body) {
    return new Promise(function (resolve, reject) {
        let requestUrl = ROOT_URL + '/' + url;
        fetch(requestUrl, httpParametersBody(method, body))
            .then((response) => {
                console.info(method + ' Url：' + requestUrl);
                console.info(method + ' Body：' + JSON.stringify(body));
                console.info('Response status:' + response.status);
                resolve(response);
            })
            .catch((err) => {
                console.info(method + ' Url：' + requestUrl);
                console.info(err);
                reject({status: -1, message: '网络异常，请检查网络后再试。'});
            })
    })
}
function fetchUrlFormData(url, method, formData) {

    return new Promise(function (resolve, reject) {
        fetch(ROOT_URL + "/" + url, {
            method: method,
            headers: {},
            body: formData
        }).then((response) => {
            if (response.ok) {
                resolve(JSON.stringify(response));
            } else {
                reject("嘿嘿嘿");
            }
        }).catch((err) => {
            reject('网络异常，请检查网络后再试。');
        })
    });

    // return new Promise(function (resolve, reject) {
    //     let requestUrl = ROOT_URL + '/' + url;
    //     fetch(requestUrl, httpParametersGet(method, formData))
    //         .then((response) => {
    //             if (response.ok) {
    //                 console.info(method + ' Url：' + requestUrl);
    //                 console.info(method + ' Params：' + JSON.stringify(formData));
    //                 console.info('Response status:' + response.status);
    //                 resolve(response);
    //             }
    //         })
    //         .catch((err) => {
    //             console.info(method + ' Url：' + requestUrl);
    //             console.info(err);
    //             reject({status: -1, message: err + '：网络异常，请检查网络后再试。'});
    //         })
    // })

}

