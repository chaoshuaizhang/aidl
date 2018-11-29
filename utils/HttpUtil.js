/**
 * Created by changePosition on 2018/11/29.
 */
export const ROOT_URL = 'http://172.16.100.158:8080/pdaware';

export default {
    fetchGet,
    fetchPost,
    fetchPut,
    ROOT_URL
};

const httpParameters = (method) => ({
    method: method,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    },
});

const httpParametersBody = (method, body = {}) => ({
    method: method,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
});

function fetchGet(url) {
    return fetchUrl(url, 'GET', null);
}

function fetchPost(url, body) {
    return fetchUrl(url, 'POST', body);
}

function fetchPut(url, body) {
    return fetchUrl(url, 'PUT', body);
}

function fetchUrl(url, method, body) {
    return new Promise(function (resolve, reject) {
        let requestUrl = ROOT_URL + '/' + url;
        fetch(requestUrl, (body === null) ? httpParameters(method) : httpParametersBody(method, body))
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

