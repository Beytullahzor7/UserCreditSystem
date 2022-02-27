import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/v1/users";

class UserService{

    //LISTE   http://localhost:8080/api/v1/users
    getUsers(){
        return axios.get(BASE_URL);
    }

    //CREATE    http://localhost:8080/api/v1/users
    createUser(user){
        return axios.post(BASE_URL, user);
    }

    //UPDATE   http://localhost:8080/api/v1/users/1
    updateUser(user, userId){
        return axios.put(BASE_URL + '/' + userId, user);
    }

    //DELETE  http://localhost:8080/api/v1/users/1
    deleteUser(userId){
        return axios.delete(BASE_URL + '/' + userId);
    }

    //CREDIT APPLICATION  http://localhost:8080/api/v1/users/1
    creditUser(userId){
        return axios.get(BASE_URL + '/' + userId);
    }

    //FIND USER  http://localhost:8080/api/v1/users/find/1
    getUserById(userId){
        return axios.get(BASE_URL + '/find' + userId);
    }

    //CREDIT QUERY  http://localhost:8080/api/v1/credits/1
    checkCredit(userIdentity){
        return axios.get('http://localhost:8080/api/v1/credits/' + userIdentity);
    }
}

export default new UserService(); 