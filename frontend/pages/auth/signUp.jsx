import React from 'react';
import {css} from '@emotion/css';
import {useForm} from "react-hook-form";

const container = css`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #eaeaea;
  min-width: 100vw;
  min-height: 100vh;

  & .main {
    border: 1px solid #999;
    background: white;

    & form {
      padding: 20px;

      & > div {
        margin-top: 10px;

        input {
          padding: 10px;
          border: 1px solid #999;
          outline: none;
          border-radius: 3px;

        }

        &:first-child {
          margin-top: 0;
        }
      }

    }

    .signInBtn {
      margin-top: 10px;

      button {
        border: 5px;
        background: #0070f3;
        width: 100%;
        color: white;
        padding: 5px;
        text-align: center;
        border: none;
        outline: none;
        border-radius: 5px;
      }
    }
  }

  & .bottom {
    margin-top: 5px;
    border: 1px solid #999;
    background: white;
    text-align: center;
    padding: 15px;
  }
`

const SignUp = () => {
  const {register, handleSubmit, watch, formState: {errors}} = useForm();

  const signUpSubmitHandler = (data) => {
    fetch("http://localhost:8080/api/v1/auth/signUp", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data)
    })
      .then(res => res.json())
      .then(res => {
        if (res.code !== 200) {
          alert(res["message"]);
        }
      });
  }

  return (
    <div className={container}>
      <div>
        <div className="main">
          <form onSubmit={handleSubmit(signUpSubmitHandler)}>
            <div>
              <input {...register("username")} placeholder="사용자 이름" name="username"/>
            </div>
            <div>
              <input type="password" {...register("password")} placeholder="비밀번호"
                     name="password"/>
            </div>
            <div>
              <input {...register("email")} placeholder="이메일" name="email"/>
            </div>
            <div>
              <input {...register("name")} placeholder="이름" name="name"/>
            </div>
            <div className="signInBtn">
              <button type="submit">가입</button>
            </div>
          </form>
        </div>
        <div className="bottom">
          <span>계정이 있으신가요?</span><a href="#">로그인</a>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
