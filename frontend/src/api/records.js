/**
 * API for records
 *
 * @author vmalyshev
 * @date 10.12.2021
 */


export const updateRecord = async (id, record) => {
    const response = await fetch('/api/record/' + id, {
        method: 'PUT',
        ...commonParams,
        body: JSON.stringify(record)
    });
    return response.json();
}

export const saveRecord = async (record) => {
    const response = await fetch('/api/record', {
        method: 'POST',
        ...commonParams,
        body: JSON.stringify(record)
    });
    return response.json();
}

export const getRecords = async () => {
    const response = await fetch('/api/records', {
        method: 'GET',
        ...commonParams
    });
    return response.json();
}

export const getRecord = async (id) => {
    const response = await fetch('/api/records/' + id, {
        method: 'GET',
        ...commonParams,
    });
    return response.json();
}

const commonParams = {
    mode: 'same-origin',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json'
    },
    referrerPolicy: 'no-referrer',
}
