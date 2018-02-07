package com.ruben.castepinyes;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import layout.fragment_pinyes_tresdecinc;
import layout.frament_fragment_pinyes;
import layout.pinya_de_cinc;
import layout.pinya_de_cuatre;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPinya.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPinya#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPinya extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button dosdecinc, pinyaTres, pinyaQuatro, pinyaCinco;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private AdView mAdView;

    public FragmentPinya() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPinya.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPinya newInstance(String param1, String param2) {
        FragmentPinya fragment = new FragmentPinya();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pinyes, container, false);
        if(R.id.fragmentPinyes==view.getId()) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");


        mAdView = view.findViewById(R.id.publi);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        dosdecinc = (Button) view.findViewById(R.id.dosdecinc);
        dosdecinc.setOnClickListener(this);
        pinyaTres = (Button) view.findViewById(R.id.pinyaTres);
        pinyaTres.setOnClickListener(this);
        pinyaQuatro = (Button) view.findViewById(R.id.pinyaQuatre);
        pinyaQuatro.setOnClickListener(this);
        pinyaCinco = (Button) view.findViewById(R.id.pinyaCinc);
        pinyaCinco.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        Toast.makeText(getActivity(), "id" + view.getId(), Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case (R.id.dosdecinc):
                fragment = new frament_fragment_pinyes();
                replaceFragment(fragment);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case R.id.pinyaTres:

                fragment = new fragment_pinyes_tresdecinc();
                replaceFragment(fragment);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                break;
            case R.id.pinyaQuatre:
                fragment = new pinya_de_cuatre();
                replaceFragment(fragment);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case R.id.pinyaCinc:
                fragment = new pinya_de_cinc();
                replaceFragment(fragment);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case 6:
                break;

        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
