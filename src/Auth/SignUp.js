import React, { useState } from "react";
import styles from "./Signup.module.css";
import { NavLink } from "react-router-dom";
 
const SignUp = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Form submitted"); 
  };

  return (
    <div className={styles.container}>
      <form onSubmit={handleSubmit} className={styles.form}>
        <h2 id={styles.headin}>Sign Up</h2>
        <label className={styles.label}>
          First Name: &emsp;
          <br />
          <input
            type="text"
            id="firstName"
            value={firstName}
            className={styles.input}
            onChange={(event) => setFirstName(event.target.value)}
            required
          />
        </label>
        <label className={styles.label}>
          Last Name: &emsp;
          <br />
          <input
            type="text"
            id="lastName"
            value={lastName}
            className={styles.input}
            onChange={(event) => setLastName(event.target.value)}
            required
          />
        </label>
        <label className={styles.label}>
          Email : &emsp;
          <br />
          <input
            type="email"
            id="email"
            value={email}
            className={styles.input}
            onChange={(event) => setEmail(event.target.value)}
            required
          />
        </label>
        <label className={styles.label}>
          User Name : &emsp;
          <br />
          <input
            type="username"
            id="username"
            value={userName}
            className={styles.input}
            onChange={(event) => setUserName(event.target.value)}
            required
          />
        </label>
        <label htmlFor="password" className={styles.label}>
          Password :
        </label>
        <input
          type="password"
          id="password"
          value={password}
          className={styles.input}
          onChange={(event) => setPassword(event.target.value)}
          required
        />
        <button type="submit" className={styles.button}>
          Submit
        </button>
        <p className={styles.login}>Already have an Account? <strong> <NavLink to="/login">Login</NavLink></strong></p>
      </form>
    </div>
  );
};

export default SignUp;