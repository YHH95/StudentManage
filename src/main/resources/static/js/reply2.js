

//async/await를 같이 이용하면 비동기 처리르 동기화된 코드처럼 작성할 수 있다.
//async는 함수 선언시에 해당 함수가 비동기 처리를 위한 함수라는 것을 명시하기 위해 사용한다.
//await는 async 함수 내에서 비동기 호출하는 부분에 사용한다.
// async function get1(sno){
//     const result = await axios.get(`/replies/list/&{sno}`)
//
//     console.log(result);
//
//     return result;
// }

async function getList({sno,page,size,goLast}){

    const result = await axios.get(`/replies/list/${sno}`,{params:{page,size}})

    console.log("들어옴")
    
    if(goLast){
        console.log("성공")
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({sno:sno,page:lastPage,size:size})
    }
    return result.data
}

async function addReply(replyObj){
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}

async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.rno}`,replyObj)
    return response.data
}


async function removeReply(rno){
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}


