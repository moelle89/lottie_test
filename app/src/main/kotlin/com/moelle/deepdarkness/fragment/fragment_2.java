package com.moelle.deepdarkness.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moelle.deepdarkness.DirectoryHelper;
import com.moelle.deepdarkness.DownloadService;
import com.moelle.deepdarkness.R;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment implements View.OnClickListener{

    // image url to download
    private static final String accent1 = "https://3.bp.blogspot.com/-EFwVj5ztKtQ/V8Qs6Viyl6I/AAAAAAAADWs/031SPYFrUnM-wreztTT4fgPe1yQj3LFhgCPcB/s1600/developer.jpg";
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 54654;


    public fragment_2() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        CardView card1 = v.findViewById(R.id.accent1);
        CardView card2 = v.findViewById(R.id.accent2);
        CardView card3 = v.findViewById(R.id.accent3);
        CardView card4 = v.findViewById(R.id.accent4);
        CardView card5 = v.findViewById(R.id.accent5);
        CardView card6 = v.findViewById(R.id.accent6);
        CardView card7 = v.findViewById(R.id.accent7);
        CardView card8 = v.findViewById(R.id.accent8);
        CardView card9 = v.findViewById(R.id.accent9);
        CardView card10 = v.findViewById(R.id.accent10);
        CardView card11 = v.findViewById(R.id.accent11);
        CardView card12 = v.findViewById(R.id.accent12);
        CardView card13 = v.findViewById(R.id.accent13);
        CardView card14 = v.findViewById(R.id.accent14);
        CardView card15 = v.findViewById(R.id.accent15);
        CardView card16 = v.findViewById(R.id.accent16);
        CardView card17 = v.findViewById(R.id.accent17);
        CardView card18 = v.findViewById(R.id.accent18);
        CardView card19 = v.findViewById(R.id.accent19);
        CardView card20 = v.findViewById(R.id.accent20);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);
        card17.setOnClickListener(this);
        card18.setOnClickListener(this);
        card19.setOnClickListener(this);
        card20.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            return v;
        }
        DirectoryHelper.createDirectory(getContext());
        return  v ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accent1: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent2: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent3: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent4: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent5: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent6: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent7: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent8: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent9: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent10: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent11: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent12: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent13: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent14: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent15: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent16: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent17: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent18: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent19: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent20: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                DirectoryHelper.createDirectory(getContext());
        }
    }
    public void Toast(){
        Toast.makeText(getActivity(), "DOWNLOAD SUCCESSFUL", LENGTH_SHORT).show();
    }
}
