import React from "react";
import "./Error.css";
import { FaExclamationTriangle } from "react-icons/fa";
const ErrorPage = () => {
  return (
    <div className="page">
      <FaExclamationTriangle className="icon" />
      <h1 className="heading">Oops! Something went wrong.</h1>
      <p className="message">We're sorry, Please try again later.</p>
      <a href="/" className="button">
        Back to Homepage
      </a>
    </div>
  );
};

export default ErrorPage;
