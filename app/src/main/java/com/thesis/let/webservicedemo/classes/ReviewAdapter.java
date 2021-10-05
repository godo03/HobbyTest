package com.thesis.let.webservicedemo.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;

import java.util.ArrayList;
import java.util.Map;

public class ReviewAdapter extends BaseAdapter {
    private final ArrayList arrayList;
    ArrayList<QuestionClass> cquestion = new ArrayList<QuestionClass>();
    int qid = 0;
    TextView answer, userAnswer;

    public ReviewAdapter(Map<String, String> map) {
        arrayList = new ArrayList();
        arrayList.addAll(map.entrySet());

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int i) {
        return (Map.Entry) arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View result;

        if (view == null) {
            result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_list_item, viewGroup, false);
        } else {
            result = view;
        }

        Map.Entry<String, String> item = getItem(i);

        String uans = item.getValue();
        String cans = item.getKey();

        if(cans.toLowerCase().contains(uans.toLowerCase())){
            ((TextView) result.findViewById(R.id.id_item_list)).setText("Correct!\n\nYou answered: " +uans);
        }
        else{
            ((TextView) result.findViewById(R.id.id_item_list)).setText("Incorrect.\n\nYou answered: " +uans);
        }

        ((TextView) result.findViewById(R.id.answer_list_item)).setText(item.getKey());
        return result;
    }
}
