import React, { useState } from "react";
import styles from "./Login.module.css";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
  };

  return (
    <div className={styles.container}>
      <form onSubmit={handleLogin} className={styles.form}>
        <h2 id={styles.headin}>Login</h2>
        <label className={styles.label}>
          Username: &emsp;
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <label className={styles.label}>
          Password: &emsp;
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <button type="submit" className={styles.button}>
          Submit
        </button>
        <p className={styles.forgot}>Forgot Password?</p>
      </form>
    </div>
  );
}

export default Login;
