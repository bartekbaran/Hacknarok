import React from 'react';
import GoogleMap from "../components/GoogleMap";
import "../styles/mainPage.css"
import List from "../components/List"


const MainPage = () => {
  return (
    <div className="container">
      <div className="map">
        <GoogleMap/>
      </div>
      <div className="list">
        <List/>
      </div>
    </div>

  );
};

export default MainPage;
