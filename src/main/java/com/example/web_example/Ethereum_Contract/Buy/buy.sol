pragma solidity ^0.4.24 <0.6.0;
/**
 * a simple of the transfer for three ways.
 */
contract buy{
    //send to account_address
    function send(address _to) public payable{
        _to.transfer(msg.value);
    }
    //send to contract address
    function sendContract() public payable{

    }
    //contract transfer to account_address
    function sendFromContract(address _to)public payable{
        _to.transfer(address(this).balance);
    }

    //get contract balance
    function getContractBalance() public view returns(uint _count){
        return address(this).balance;
    }
    //get account_address balance
    function getAddressBalance() public view returns(uint _count){
        return msg.sender.balance;
    }
}