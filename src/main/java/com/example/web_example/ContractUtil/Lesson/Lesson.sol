pragma solidity ^0.4.24 <0.6.0;

contract lesson{
    struct user{
        string name;
        uint age;
    }
    mapping(address => user) users;

    event userInfo(string name,uint age);

    function addNewUser(string _name, uint _age) public returns(string , uint){
        users[msg.sender].name = _name;
        users[msg.sender].age = _age;
        emit userInfo(users[msg.sender].name,users[msg.sender].age);
        return (users[msg.sender].name,users[msg.sender].age);
    }

    function changeAge(uint _newage) public returns(string,uint){
        users[msg.sender].age = _newage;
        emit userInfo(users[msg.sender].name,users[msg.sender].age);
        return (users[msg.sender].name,users[msg.sender].age);
    }

    function getUserInfo() public view returns(string, uint){
        return (users[msg.sender].name,users[msg.sender].age);
    }
}