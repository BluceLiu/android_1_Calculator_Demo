package cn.edu.tsinghua.training.demo;

import cn.edu.tsinghua.training.demo.util.ExpressionParser;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText expression;
	private Button del, equal, add, sub, mul, div, dot, d1, d2, d3, d4, d5, d6, d7, d8, d9, d0; 
	private Button[] buttons = {add, sub, mul, div, dot, d1, d2, d3, d4, d5, d6, d7, d8, d9, d0};//单击事件相同的按钮
	private int[] ids = {R.id.add, R.id.sub, R.id.mul, R.id.div, R.id.dot, R.id.d1, R.id.d2, R.id.d3, R.id.d4,
			R.id.d5, R.id.d6, R.id.d7, R.id.d8, R.id.d9, R.id.d0};//和按钮数组元素位置对应，便于循环初始化
	private String[] strings = {"+", "-", "*", "/", ".", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	private static boolean b = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();//调用初始化方法
		setButtons();
	}
	
	private void init() {//完成所有控件的初始化
		expression = (EditText) findViewById(R.id.expression);
		del = (Button) findViewById(R.id.del);
		equal = (Button) findViewById(R.id.equal);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);//循环给按钮数组的按钮进行初始化
		}
	}
	
	private void setButtons() {
		del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (b) {
					String expressionString = expression.getText().toString();
					if (expressionString.length()>0) {
						expression.setText(expressionString.substring(0, expressionString.length()-1));
						expression.setSelection(expression.getText().toString().length());
					}
				} else {
					expression.setText("");
					b = true;
				}
			}
		});
		equal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				expression.setText(ExpressionParser.compute(expression.getText().toString()));
				expression.setSelection(expression.getText().toString().length());
				b = false;
			}
		});
		//关键  给按钮数组的每一个按钮添加单击事件，完成对表达式的编辑
		for (int i = 0; i < buttons.length; i++) {
			final String string = strings[i];
			buttons[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					expression.setText(expression.getText().toString() + string);
					expression.setSelection(expression.getText().toString().length());
				}
			});
		}
	}
// 0123456 substring(0,s.lenght-1)=6
//i=indexof("+-*/")  a=substring(0,i)  b=substring(i+1)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
