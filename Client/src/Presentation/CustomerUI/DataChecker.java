package Presentation.CustomerUI;

import javafx.scene.control.Alert;

public class DataChecker {


    /**
     * 名字栏未填写
     */
    public void inValidName(){
        Alert inValidName=new Alert(Alert.AlertType.ERROR);
        inValidName.setHeaderText(null);
        inValidName.setTitle("信息不完整");
        inValidName.setContentText("您未填写客户姓名，请填写后重新保存");
    }

    /**
     * 操作员未填写
     */
    public void inValidClerk(){
        Alert inValidClerk=new Alert(Alert.AlertType.ERROR);
        inValidClerk.setHeaderText(null);
        inValidClerk.setTitle("信息不完整");
        inValidClerk.setContentText("您未填写操作员，请填写后重新保存");
    }


    /**
     * 新增客户失败
     */
    public void FailToAdd(){
        Alert failToAdd=new Alert(Alert.AlertType.ERROR);
        failToAdd.setHeaderText(null);
        failToAdd.setTitle("链接发生错误");
        failToAdd.setContentText("新增客户失败( ･᷄ὢ･᷅ )");
    }
}
