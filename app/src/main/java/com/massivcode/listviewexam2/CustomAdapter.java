package com.massivcode.listviewexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Copyright 2016 Pureum Choe
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * <p/>
 * Created by Ray Choe on 2016-01-11.
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<Account> mData;
    private LayoutInflater mInflater;

    public CustomAdapter(ArrayList<Account> mData, Context context) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout, parent, false);
            holder.textView1 = (TextView) convertView.findViewById(R.id.item_tv);
            holder.textView2 = (TextView) convertView.findViewById(R.id.item_tv2);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Account account = (Account) getItem(position);
        holder.textView1.setText("계좌명 : " + account.getId());
        holder.textView2.setText("잔액 : " + account.getBalance());

        return convertView;
    }

    static class ViewHolder {
        TextView textView1;
        TextView textView2;
    }
}
