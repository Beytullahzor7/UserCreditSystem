import logo from './logo.svg';
import './App.css';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import ListUserComponent from './components/ListUserComponent';
import CreateUserComponent from './components/CreateUserComponent';
import ApplyUserCreditComponent from './components/ApplyUserCreditComponent';


function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />

        <div className='container'>
          <Switch>
            <Route path="/" exact component={ListUserComponent}></Route>
            <Route path="/users" component={ListUserComponent}></Route>
            <Route path="/add-user/:id" component={CreateUserComponent}></Route>
            <Route path="/apply-user/:id" component={ApplyUserCreditComponent}></Route>
          </Switch>
        </div>
        <FooterComponent/>
      </Router>
    </div>

  );
}

export default App;
