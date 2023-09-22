import number,  {str} from './module.js'

console.log(number);


setTimeout(()=> {
    import('./module.js').then(abc =>{
        console.log(abc);
    })
})


setTimeout(async ()=>{
    const abc = await import('./module')
    console.log(abc);
})



