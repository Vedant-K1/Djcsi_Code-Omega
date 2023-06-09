import React, { useEffect, useState } from 'react'
import { NavLink } from "react-router-dom";

const Login = () => {
  //   const [username, setUsername]=useState('')
  //   const [pass, setpass]=useState('')
  //   const [email, setemail]=useState('')

  // async function submit(){
  //   email.preventDefault();
  //   try{
  //     await axios.post("http://localhost:8000/",{
  //       username,email,pass
  //     }).then(res=>{
  //       if(res.data=="exist"){
  //         history("/home",{state:{id:email}})
  //       }else if(res.data="notexist"){
  //         alert("User have not registered")
  //       }
  //     })
  //   }catch(e){
  //     console.log(e)
  //   }
  // }


  return (
    <div className="min-h-screen bg-gray-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8" style={{ alignContent: 'center', alignItems: 'center' }}>
      <div className='flex flex-col justify-center' style={{ height: '500px', width: '500px', alignItems: 'center', border: '2px solid #ef4444', borderRadius: '25px', background: '#f57f58' }}>
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <p className='items-center mt-8 ml-44 log text-5xl' style={{ transform: 'translateX(-13px)' }}><strong>KidSecure</strong></p>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900 ">
            Sign in to your account
          </h2>
        </div>

        <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
          <div className="py-8 px-4 shadow sm:rounded-lg sm:px-10">
            <form className="space-y-6">
              <div>
                <label
                  htmlFor="email"
                  className="block text-sm font-medium text-gray-700"
                >
                  Email address
                </label>
                <div className="mt-1">
                  <input
                    id="email"
                    name="email"
                    type="email"
                    autoComplete="email"
                    required
                    className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  />
                </div>
              </div>

              <div>
                <label
                  htmlFor="password"
                  className="block text-sm font-medium text-gray-700"
                >
                  Password
                </label>
                <div className="mt-1">
                  <input
                    id="password"
                    name="password"
                    type="password"
                    autoComplete="current-password"
                    required
                    className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                  />
                </div>
              </div>

              <div>
                <NavLink to="/parent"> <button
                  type="submit"
                  className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                >
                  Sign in
                </button></NavLink>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Login