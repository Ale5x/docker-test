import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from "react";
import axios from 'axios';  //npm install axios

function App() {
  const [items, setItems] = useState([]);
  const[text, setText] = useState("");
  const [error, setError] = useState("");
  const [fetching, setFetching] = useState(true);
  useEffect(() => {

    if(fetching) {
       axios.get(`http://localhost:8080/text/list`)
       .then(response => {
        console.log("RESPONSE -->> ", response)
           setItems([...response.data]);
       })
       .catch(e => {
           if(e.response.status === 404) {
              setError("error 404")
           }else {
            setError("error somethin")
           }
       })
       .finally(() => {
       setFetching(false)});
    }
}, [fetching])

const add = () => {
  axios.post(`http://localhost:8080/text/add`, {text: text})
       .then(response => {
        console.log("RESPONSE ADDING -->> ", response)
       })
       .catch(e => {
           if(e.response.status === 404) {
              setError("error 404")
           }else {
            setError("error somethin")
           }
       })
       .finally(() => {
       setFetching(true)});
}

const del = (e) => {
  axios.post(`http://localhost:8080/text/delete`, {textId: e.target.value})
       .then(response => {
        console.log("RESPONSE REMOVING -->> ", response)
       })
       .catch(e => {
           if(e.response.status === 404) {
              setError("error 404")
           }else {
            setError("error somethin")
           }
       })
       .finally(() => {
       setFetching(true)});
}

  return (
    <div className="App">
    
      <body>
        <input type="text" onChange={e => setText(e.target.value)}></input>
        <button onClick={add}>ADD</button>
      <h1>{error}</h1>
        <h1>Test</h1>
      {items.map(item =>
        <div key={item.textId} className="item">
      
          <ui>
            <li><h1>{item.text}</h1></li>
            <li><button className="btn-delete" onClick={del} value={item.textId}>DELETE</button></li>
          </ui>
        </div>
        )}
      </body>
    </div>
  );
}

export default App;
