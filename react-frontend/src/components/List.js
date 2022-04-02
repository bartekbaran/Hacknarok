import React from 'react';
import "../styles/list.css"
import ListItem from "./ListItem";

const List = () => {
  const test = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]

  return (
    <div className="list" style={{maxHeight: window.innerHeight, overflow: "auto"}}>
      {test.map((item) => (
        <ListItem key={item}/>
      ))
      }

    </div>


  );
};

export default List;
