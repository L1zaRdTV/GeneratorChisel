package com.example.generatorchisel;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    private final UniqueNumberGenerator generator = new UniqueNumberGenerator();

    private EditText countInput;
    private EditText minInput;
    private EditText maxInput;
    private TextView messageView;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createContentView());
    }

    private ScrollView createContentView() {
        ScrollView scrollView = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        int padding = dp(20);
        root.setPadding(padding, padding, padding, padding);
        scrollView.addView(root);

        TextView title = createTitle();
        root.addView(title);

        countInput = createNumberInput("Количество чисел (1–10 000)");
        minInput = createNumberInput("Минимальное значение");
        maxInput = createNumberInput("Максимальное значение");
        root.addView(countInput);
        root.addView(minInput);
        root.addView(maxInput);

        Button generateButton = new Button(this);
        generateButton.setText("Сгенерировать");
        generateButton.setOnClickListener(view -> generateNumbers());
        root.addView(generateButton, matchWidthParams());

        messageView = new TextView(this);
        resultView = new TextView(this);
        resultView.setTextIsSelectable(true);
        root.addView(messageView, matchWidthParams());
        root.addView(resultView, matchWidthParams());
        return scrollView;
    }

    private TextView createTitle() {
        TextView title = new TextView(this);
        title.setText("Генератор уникальных чисел");
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        return title;
    }

    private EditText createNumberInput(String hint) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setSingleLine(true);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        input.setLayoutParams(matchWidthParams());
        return input;
    }

    private void generateNumbers() {
        try {
            int count = parseInput(countInput, "Введите количество чисел.");
            int min = parseInput(minInput, "Введите минимальное значение.");
            int max = parseInput(maxInput, "Введите максимальное значение.");
            List<Integer> numbers = generator.generate(count, min, max);
            showSuccess(numbers);
        } catch (IllegalArgumentException error) {
            showError(error.getMessage());
        }
    }

    private int parseInput(EditText input, String emptyMessage) {
        String value = input.getText().toString().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(emptyMessage);
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Введите целое число: " + input.getHint());
        }
    }

    private void showSuccess(List<Integer> numbers) {
        messageView.setText("Сгенерировано уникальных чисел: " + numbers.size());
        resultView.setText(joinNumbers(numbers));
    }

    private void showError(String message) {
        messageView.setText("Ошибка: " + message);
        resultView.setText("");
    }

    private String joinNumbers(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(numbers.get(i));
        }
        return builder.toString();
    }

    private LinearLayout.LayoutParams matchWidthParams() {
        return new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
