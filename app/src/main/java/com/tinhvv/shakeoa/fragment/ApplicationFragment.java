package com.tinhvv.shakeoa.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;

import com.tinhvv.shakeoa.R;
import com.tinhvv.shakeoa.view.ShakeOAViewFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ray-jason on 22/02/2016.
 */
public class ApplicationFragment extends Fragment implements View.OnClickListener {

    private SearchView.OnQueryTextListener mSearchListener;
    private SearchView mSearchApp;
    private ListView mListView;
    private ApplicationAdapter mApplicationAdapter;
    private PackageManager packageManager = null;

    private List<ApplicationInfo> mApplicationInstalledList;

    public static final ApplicationFragment newInstance(String message) {
        ApplicationFragment fragment = new ApplicationFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString("123", message);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_application, container, false);

        initViews(v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
        initValues();
    }

    private void initViews(View v) {
        if (v != null) {
            mSearchApp = (SearchView) v.findViewById(R.id.searchViewApplication);
            mListView = (ListView) v.findViewById(R.id.listViewApplication);
        }
    }

    private void initValues() {

        packageManager = getActivity().getPackageManager();
        //Init for ListView
        new LoadApplications().execute();
    }

    private void initListeners() {
        //Init for ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int searchView_initPosY = mSearchApp.getScrollY();

                if (i == SCROLL_STATE_TOUCH_SCROLL) {
//                    mSearchApp.setVisibility(View.GONE);
                    mSearchApp.animate().cancel();
                    mSearchApp.animate().translationYBy(mSearchApp.getHeight());
                } else {
                    mSearchApp.animate().cancel();
                    mSearchApp.animate().translationYBy(searchView_initPosY);
                    mSearchApp.setTranslationY(searchView_initPosY);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        mSearchListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (mApplicationAdapter != null) {
                    mApplicationAdapter.getFilter().filter(query);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mApplicationAdapter != null) {
                    mApplicationAdapter.getFilter().filter(newText);
                    return true;
                }
                return false;
            }
        };
        mSearchApp.setSubmitButtonEnabled(true);
        mSearchApp.setOnQueryTextListener(mSearchListener);
    }

    @Override
    public void onClick(View v) {

    }

    private List<ApplicationInfo> getAllApplicationInstalled(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(600);
                //
                if (packageManager != null) {
                    mApplicationInstalledList = getAllApplicationInstalled(packageManager.
                            getInstalledApplications(PackageManager.GET_META_DATA));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mApplicationInstalledList != null) {
                //Sort list
                Collections.sort(mApplicationInstalledList, new Comparator<ApplicationInfo>() {
                    @Override
                    public int compare(ApplicationInfo applicationInfo1, ApplicationInfo applicationInfo2) {
                        String nameApp1 = applicationInfo1.loadLabel(packageManager).toString();
                        String nameApp2 = applicationInfo2.loadLabel(packageManager).toString();
                        if (nameApp1 != null && nameApp2 != null) {
                            return nameApp1.compareTo(nameApp2);
                        }
                        return 0;
                    }
                });
                //
                mApplicationAdapter = new ApplicationAdapter(getActivity(),
                        R.layout.app_item_row, mApplicationInstalledList);
                mListView.setAdapter(mApplicationAdapter);
            }
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
            hideLoadingView();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            showLoadingView();
//            progress = ProgressDialog.show(getActivity(), null,
//                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> implements Filterable {

        private Context mContext;
        private List<ApplicationInfo> mApplicationInfoList;
        private List<ApplicationInfo> filteredList;
        private ApplicationFilter applicationFilter;

        public ApplicationAdapter(Context context, int resource, List<ApplicationInfo> mApplicationInfoList) {
            super(context, resource, mApplicationInfoList);
            this.mContext = context;
            this.mApplicationInfoList = mApplicationInfoList;
            this.filteredList = mApplicationInfoList;
            //
            getFilter();
        }

        @Override
        public int getCount() {
            if (filteredList != null) {
                return filteredList.size();
            }
            return 0;
        }

        @Override
        public ApplicationInfo getItem(int position) {
            if (filteredList != null) {
                return filteredList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ShakeOAViewFactory.ApplicationRow row = null;
            if (convertView == null) {
                row = new ShakeOAViewFactory.ApplicationRow(getActivity());
            } else {
                row = (ShakeOAViewFactory.ApplicationRow) convertView;
            }

            ApplicationInfo appInfo = getItem(position);
            row.clearContent();
            if (appInfo != null) {
                if (packageManager != null) {
                    CharSequence strNameApp = appInfo.loadLabel(packageManager);
                    if (strNameApp != null) {
                        row.mAppName.setText(strNameApp);
                    }
                    Drawable iconDrawable = appInfo.loadIcon(packageManager);
                    if (iconDrawable != null) {
                        row.mImageIcon.setImageDrawable(iconDrawable);
                    }
                }
            }
            return row;
        }

        /**
         * Get custom filter
         *
         * @return filter
         */
        @Override
        public Filter getFilter() {
            if (applicationFilter == null) {
                applicationFilter = new ApplicationFilter();
            }
            return applicationFilter;
        }

        /**
         * Custom filter for friend list
         * Filter content in friend list according to the search text
         */
        private class ApplicationFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null && constraint.length() > 0) {
                    ArrayList<ApplicationInfo> tempList = new ArrayList<>();

                    // search content in ContactWarning list
                    for (ApplicationInfo applicationInfo : mApplicationInstalledList) {
                        String nameApp = applicationInfo.loadLabel(packageManager).toString();
                        if (nameApp != null) {
                            if (nameApp.toLowerCase().contains(constraint.toString().toLowerCase())) {
                                tempList.add(applicationInfo);
                            }
                        }
                    }

                    filterResults.count = tempList.size();
                    filterResults.values = tempList;
                } else {
                    filterResults.count = mApplicationInstalledList.size();
                    filterResults.values = mApplicationInstalledList;
                }

                return filterResults;
            }

            /**
             * Notify about filtered list to ui
             *
             * @param constraint text
             * @param results    filtered result
             */
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<ApplicationInfo>) results.values;
                if (filteredList != null) {
                    notifyDataSetChanged();
                }
            }
        }
    }


    protected ShakeOAViewFactory.LoadingView mLoadingView;

    protected void showLoadingView() {
        Log.d("ApplicationFragment", "showLoadingView");

        if (getActivity() != null && getView() != null)

            if (mLoadingView != null) {
                hideLoadingView();
            }

        if (getActivity() != null) {
            mLoadingView = new ShakeOAViewFactory.LoadingView(getActivity());

            ViewGroup decor = (ViewGroup) getActivity().getWindow()
                    .getDecorView();
            decor.addView(mLoadingView);
        }
    }

    protected void hideLoadingView() {
        Log.d("ApplicationFragment", "hideLoadingView");

        if (mLoadingView == null)
            return;

        ((ViewGroup) (mLoadingView.getParent())).removeView(mLoadingView);
        mLoadingView = null;
    }

}
