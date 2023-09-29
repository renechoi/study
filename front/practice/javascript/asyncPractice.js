const a = () => console.log(1)
const b = () => console.log(2)

a()
b()


const c = (callback) => {
    setTimeout(() => {
        console.log(3);
        callback();
    }, 1000)
}


const getMovies = (movieName, callback) => {
    fetch("https://www.omdbapi.com/?apikey=1234").then(res => res.json).then(
        res => {
            console.log(res)
            callback()
        }
    )
}

// 콜백 지옥 -> 동기를 보장하지만 계속된 들여쓰기
getMovies('frozen', () => {
        console.log('겨울왕국')
        getMovies('avengers', () => {
            console.log('어벤져스')
        })
    }
)


// promise를 사용한 해결

const p = () => {
    return new Promise(resolve => {
        setTimeout(()=>{
            console.log(1)
            resolve()
        }, 1000)
    })
}

const j = () => console.log(2)

const h = () => {
    return new Promise(resolve => {
        setTimeout(()=>{
            console.log(1)
            resolve()
        }, 1000)
    })
}


p().then(()=>{j()})
p().then(()=>h()).then(()=>j())

p().then(h).then(j)



// await를 사용하여 기다리기
await a();
b();

const wrap = async () => {
    await a()
    b()
}

wrap();



const delayAdd = (index, cb, errorCb) =>{
    setTimeout(()=>{
        if (index > 10){
            errorCb(`${index}는 10보다 클 수 없습니다.`)
            return;
        }
        console.log(index);
        cb(index+1);
    }, 1000)
}

delayAdd((4, res=>console.log(res), err=>console.error(err)));
delayAdd((13, res=>console.log(res), err=>console.error(err)));




const delayAdd2 = index => {
    return new Promise((resolve, reject) => {
        setTimeout( ()=> {
            if (index > 10){
                reject (`${index}`)
                return
            }
            console.log(index)
            resolve(index +1 )
        }, 1000)
    })
}

delayAdd2(2)
.then(res => console.log(res))
.catch(err => console.error(err))
.finally(()=> console.log('done'))

const wrap2 = async () => {
    try {
        const res = await delayAdd2(2)
        console.log(res)
    } catch (err){
        console.log(err)
    }
}

wrap2()





const titles = ['frozen', 'avengers', 'avatar']
titles.forEach(async title => {
    const movies = await getMovies(title);
    console.log(movies);
})